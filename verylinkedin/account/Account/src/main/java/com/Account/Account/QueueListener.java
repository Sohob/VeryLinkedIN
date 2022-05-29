package com.Account.Account;

import com.Account.ICommand;
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


    @RabbitListener(queues = "accountApp")
    public String getMessage(Message message) {
        try {
            String newMsg = new String(message.getBody());
            System.out.println(map);
            System.out.println(newMsg);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> msgMap = objectMapper.readValue(newMsg, Map.class);
            System.out.println(msgMap);
            String commandName = (String) msgMap.get("Command");
            Map<String, Object> data = (Map<String, Object>) msgMap.get("data");
            System.out.println(data);
            ICommand cmd = map.get(commandName);

            return cmd.execute(data).toString();
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
    }
}
