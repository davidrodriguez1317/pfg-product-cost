package com.dro.pfg.pfgproductcost.service;

import com.dro.pfg.pfgproductcost.model.ProductDto;
import com.dro.pfg.pfgproductcost.model.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.dro.pfg.pfgproductcost.config.Constants.*;

@Slf4j
@Service
@EnableRabbit
@RequiredArgsConstructor
public class MessagingService {

    private final RabbitTemplate rabbitTemplate;
    private final ProductService productService;


    @RabbitListener(queues = PRODUCT_CONSUME_QUEUE)
    public void consumeMessage(ProductRequestDto productRequest) {
        log.info("Messaging flow. Getting product cost for id {} and currency {}", productRequest.getId(), productRequest.getCurrency());
        ProductDto product = productService.getProductById(productRequest.getCurrency(), productRequest.getId());

        log.info("Messaging flow. Product information got for id {} and currency {}: {}", productRequest.getId(), productRequest.getCurrency(), product);
        publishMessage(product);
    }

    public void publishMessage(ProductDto product) {
        rabbitTemplate.convertAndSend(PRODUCT_EXCHANGE, PRODUCT_PUBLISH_ROUTING_KEY, product);
        log.info("Messaging flow. Product published: {}", product);
    }
}
