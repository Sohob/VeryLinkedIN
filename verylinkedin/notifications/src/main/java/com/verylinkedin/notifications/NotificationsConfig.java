package com.verylinkedin.notifications;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationsConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.notifications}")
    private String notificationsQueue;

    @Value("${rabbitmq.routing-keys.internal-notifications}")
    private String internalNotificationsRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue notificationsQueue() {
        return new Queue(this.notificationsQueue);
    }

    @Bean
    public Binding internalToNotificationsBinding() {
        return BindingBuilder
                .bind(notificationsQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationsRoutingKey);
    }


    public String getInternalExchange() {
        return internalExchange;
    }

    public String getNotificationsQueue() {
        return notificationsQueue;
    }

    public String getInternalNotificationsRoutingKey() {
        return internalNotificationsRoutingKey;
    }
}
