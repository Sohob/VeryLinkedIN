package com.verylinkedin.notifications;

import com.verylinkedin.notifications.commands.AddNotificationCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


@Service
@AllArgsConstructor
@Slf4j
public class NotificationsConsumer {

    @RabbitListener(queues = "${rabbitmq.queues.notifications}",concurrency = "${rabbitmq.consumers}-${rabbitmq.max-consumers}")
            public static  Object consumer(Message messageFromQueue)  {
        try{
            JSONObject body= new JSONObject(new String(messageFromQueue.getBody()));
            String type=(String) messageFromQueue.getMessageProperties().getHeaders().get("__TypeId__");
            String[] split=type.split(".");
            System.out.println(split);
            System.out.println(type);
            String typeId=type.split("\\.")[type.split("\\.").length-1];
            System.out.println(typeId);
            Class commandClass = CommandMap.getInstance().getCommandClass(typeId);
            Constructor commandConstructor = commandClass.getConstructor(JSONObject.class);
            Command commandObject = (Command) commandConstructor.newInstance(body);
            Object response = commandObject.execute();
            return response;

    }catch(Exception e){
            System.out.println("---------------------------------------------------------------");
            System.out.println(e);
            return "Error sending notification";
        }
    }
}
