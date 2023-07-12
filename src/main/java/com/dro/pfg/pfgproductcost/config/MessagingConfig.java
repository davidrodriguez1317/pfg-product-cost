package com.dro.pfg.pfgproductcost.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import static com.dro.pfg.pfgproductcost.config.Constants.*;

@Configuration
@Slf4j
public class MessagingConfig {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    //@Lazy
    public TopicExchange productExchange() {
        return new TopicExchange (PRODUCT_EXCHANGE);
    }

    @Bean
    //@Lazy
    public Queue publishQueue() {
        return new Queue(PRODUCT_PUBLISH_QUEUE);
    }

    @Bean
    //@Lazy
    public Queue consumeQueue() {
        return new Queue(PRODUCT_CONSUME_QUEUE);
    }

    @Bean
    public Binding binding(Queue publishQueue, TopicExchange myExchange) {
        return BindingBuilder.bind(publishQueue)
                .to(myExchange).with(PRODUCT_PUBLISH_ROUTING_KEY);
    }

}
