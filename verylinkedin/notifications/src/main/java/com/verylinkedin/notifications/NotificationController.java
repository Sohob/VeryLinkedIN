package com.verylinkedin.notifications;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class NotificationController {

    @RabbitListener(queues = "${rabbitmq.queues.notifications}")
            public static  void Notification(Message messageFromQueue)  {
        try{


        String typeId = (String) messageFromQueue.getMessageProperties().getHeaders().get("__TypeId__");
        System.out.println(typeId);
        JSONObject requestJSON = new JSONObject(new String(messageFromQueue.getBody()));
        switch (typeId){
            case "com.verylinkedin.core.requests.NotificationList":
                System.out.println(requestJSON.get("users"));
                JSONArray arr=requestJSON.getJSONArray("users");
                System.out.println(arr);
                String[] users=new String[arr.length()];
                for(int i=0;i<arr.length();i++){
                    users[i]= arr.getString(i);
                }
                NotificationList notificationList=new NotificationList(requestJSON.getString("message"),users );
                AddNotificationList addNotificationList=new AddNotificationList(notificationList);
                addNotificationList.execute();
                break;
            case "com.verylinkedin.core.requests.Notification":
                Notification notification=new Notification(requestJSON.getString("message"),requestJSON.getString("to"));
                AddNotification addNotification=new AddNotification(notification);
                addNotification.execute();
                break;
        }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
