package com.verylinkedin.groupchat;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroupsConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.groups}")
    private String groupsQueue;

    @Value("${rabbitmq.routing-keys.internal-groups}")
    private String internalGroupsRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue groupsQueue() {
        return new Queue(this.groupsQueue);
    }

    @Bean
    public Binding internalToGroupsBinding() {
        return BindingBuilder
                .bind(groupsQueue())
                .to(internalTopicExchange())
                .with(this.internalGroupsRoutingKey);
    }


    public String getInternalExchange() {
        return internalExchange;
    }

    public String getGroupsQueue() {
        return groupsQueue;
    }

    public String getInternalGroupsRoutingKey() {
        return internalGroupsRoutingKey;
    }
}
