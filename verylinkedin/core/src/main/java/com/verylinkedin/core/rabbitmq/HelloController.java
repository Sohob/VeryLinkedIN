package com.verylinkedin.core.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class HelloController {
    @Autowired
    RabbitTemplate rabbitTemplate;



    @GetMapping("/send")
    public HashMap hello(@RequestParam(required = true) String message) {
        //将来要发送的消息对象
        Message msg = MessageBuilder.withBody(message.getBytes()).build();
        //发送消息，方法的返回值就是 server 给出的响应
        String result = (String) rabbitTemplate.convertSendAndReceive(RabbitConfig.RPC_EXCHANGE, RabbitConfig.RPC_MSG_QUEUE, msg);

           // System.out.println("resceced：" + new String(result.getBody()));
        return  convertStringtoMap(result)

    }
    public @ResponseBody
    ResponseEntity<String> viewChatResponse(ResponseEntity<String> message) {
        return message;
    }



}