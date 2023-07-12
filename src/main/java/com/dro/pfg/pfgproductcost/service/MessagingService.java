package com.dro.pfg.pfgproductcost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@RequiredArgsConstructor
public class RabbitMqService {

    private final RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = "my-queue")
    public void consumeMessage(String message) {
        System.out.println("Recibido: " + message);
    }

    public void publishMessage(String message) {
        rabbitTemplate.convertAndSend("amq.topic", "my-routing-key", message);
        System.out.println("Mensaje publicado: " + message);
    }
}
