package com.verylinkedin.mypost;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.groups}")
    private String postQueue;

    @Value("${rabbitmq.routing-keys.internal-groups}")
    private String internalPostRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue groupsQueue() {
        return new Queue(this.postQueue);
    }

    @Bean
    public Binding internalToGroupsBinding() {
        return BindingBuilder
                .bind(groupsQueue())
                .to(internalTopicExchange())
                .with(this.internalPostRoutingKey);
    }


    public String getInternalExchange() {
        return internalExchange;
    }

    public String getGroupsQueue() {
        return postQueue;
    }

    public String getInternalGroupsRoutingKey() {
        return internalPostRoutingKey;
    }
}
