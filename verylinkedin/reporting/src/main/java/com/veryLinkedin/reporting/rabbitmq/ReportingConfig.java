package com.veryLinkedin.reporting.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportingConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.reports}")
    private String reportsQueue;

    @Value("${rabbitmq.routing-keys.internal-reports}")
    private String internalReportsRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue groupsQueue() {
        return new Queue(this.reportsQueue);
    }

    @Bean
    public Binding internalToGroupsBinding() {
        return BindingBuilder
                .bind(groupsQueue())
                .to(internalTopicExchange())
                .with(this.internalReportsRoutingKey);
    }


    public String getInternalExchange() {
        return internalExchange;
    }

    public String getReportsQueue() {
        return reportsQueue;
    }

    public String getInternalReportsRoutingKey() {
        return internalReportsRoutingKey;
    }
}
