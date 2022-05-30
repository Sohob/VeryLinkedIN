package com.verylinkedin.mypost.rabbitmq;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jiangnan a little rain
 * @WeChat public account Jiangnan a little rain
 *@website http://www.itboyhub.com
 * @International Station http://www.javaboy.org
 * @WeChat a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Component
public class RpcServer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.RPC_MSG_QUEUE)
    public String process(Message message) {
        byte[] body = message.getBody();
        //这是服务端要返回的消息
      return new String(body);
    }
}