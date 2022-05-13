package com.verylinkedin.core;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = "#{queue.name}")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    @RabbitListener(queues = "#{sendMessage.name}")
    public void receiveMessage2(String message) {
        System.out.println("Received2 <" + message + ">");
        latch.countDown();
    }


    public CountDownLatch getLatch() {
        return latch;
    }

}