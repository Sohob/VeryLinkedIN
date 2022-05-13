package com.verylinkedin.groupchat;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GroupChatApplication {
    public static final String topicExchangeName = "spring-boot-exchange";
    public static final String topicExchangeName2 = "spring-shoes-exchange";

    static final String queueName = "spring-boot";
    static final String queueName2 = "send-message";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }
    @Bean
    Queue sendMessage() {
        return new Queue(queueName2, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    TopicExchange exchange2() {
        return new TopicExchange(topicExchangeName2);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }
    @Bean
    Binding binding2(Queue sendMessage, TopicExchange exchange2) {
        return BindingBuilder.bind(sendMessage).to(exchange2).with("foo.far.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "sendMessage");
    }

    public static void main(String[] args) {SpringApplication.run(GroupChatApplication.class, args);}
}
