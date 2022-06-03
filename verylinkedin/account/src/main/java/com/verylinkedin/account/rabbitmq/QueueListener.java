package com.verylinkedin.account.rabbitmq;

import com.verylinkedin.account.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QueueListener {

    private final Map<String, ICommand> map;

    public QueueListener(Map<String, ICommand> map) {
        this.map = map;
    }


    @RabbitListener(queues = RabbitConfig.QUEUE_NAME , returnExceptions = "true")
    public String getMessage(Message message) {
        System.out.println("The Rabbit listened");

        try {
            System.out.println("msg: " +message);
            String newMsg = new String(message.getBody());
            System.out.println("map: " +map);
            System.out.println("newMsg: " +newMsg);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> msgMap = objectMapper.readValue(newMsg, Map.class);
            System.out.println("msgMap: " +msgMap);
            String commandName = (String) msgMap.get("Command");
            System.out.println("command name: " +commandName);

            Map<String, Object> data = (Map<String, Object>) msgMap.get("data");
            System.out.println("Data: " +data);
            ICommand cmd = map.get(commandName);

            return cmd.execute(data).toString();
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
    }
}
