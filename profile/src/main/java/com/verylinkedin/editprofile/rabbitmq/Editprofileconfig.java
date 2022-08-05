package com.verylinkedin.editprofile.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Editprofileconfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.editprofile}")
    private String editprofileQueue;

    @Value("${rabbitmq.routing-keys.internal-editprofile}")
    private String internaleditprofileRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue groupsQueue() {
        return new Queue(this.editprofileQueue);
    }

    @Bean
    public Binding internalToGroupsBinding() {
        return BindingBuilder
                .bind(groupsQueue())
                .to(internalTopicExchange())
                .with(this.internaleditprofileRoutingKey);
    }


    public String getInternalExchange() {
        return internalExchange;
    }

    public String getGroupsQueue() {
        return editprofileQueue;
    }

    public String getInternalGroupsRoutingKey() {
        return internaleditprofileRoutingKey;
    }
}
