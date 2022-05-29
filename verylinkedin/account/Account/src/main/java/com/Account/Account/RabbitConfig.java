package com.Account.Account;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public final static String QUEUE_NAME = "accountApp";
    public final static String TOPIC_NAME = "exchange";
    public final static String ROUTING_KEY = "accountKey";

//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME);
//    }
//
//    @Bean
//    public TopicExchange exchange() {
//        return new TopicExchange(TOPIC_NAME);
//    }

//    @Bean
//    public Binding binding(Queue queue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
//    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setUsername("guest");
//        cachingConnectionFactory.setPassword("guest");
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    public AmqpTemplate template(ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter());
//        return rabbitTemplate;
//    }
}
