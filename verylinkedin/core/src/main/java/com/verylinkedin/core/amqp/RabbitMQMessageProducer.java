package com.verylinkedin.core.amqp;

import com.verylinkedin.core.responses.ViewChatResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AsyncAmqpTemplate;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AsyncRabbitTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertSendAndReceive(exchange, routingKey, payload);
        log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
    }

    public AsyncRabbitTemplate.RabbitConverterFuture<String> publishAndReceive(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        AsyncRabbitTemplate.RabbitConverterFuture<String> res = amqpTemplate.convertSendAndReceiveAsType(exchange, routingKey, payload, new ParameterizedTypeReference<>() {
        });
        log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        // Convert Byte stream to JSONObject
        //JSONObject responseJSON = new JSONObject(new String(res));
        //log.info("Received {} of type {}", res, res.getClass());
        return res;
    }

}