package com.verylinkedin.core.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducerV2 {

   // private final AsyncRabbitTemplate asyncRabbitTemplate;
    // @Autowired
    private final RabbitTemplate template;
//    public void publish(Object payload, String exchange, String routingKey) {
//        log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
//        asyncRabbitTemplate.convertSendAndReceive(exchange, routingKey, payload);
//        log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
//    }

    public byte[] publishAndReceive(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        byte[] res = (byte[]) template.convertSendAndReceive(exchange, routingKey, payload);
        log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        // Convert Byte stream to JSONObject
        //JSONObject responseJSON = new JSONObject(new String(res));
        //log.info("Received {} of type {}", res, res.getClass());
        return res;
    }

}