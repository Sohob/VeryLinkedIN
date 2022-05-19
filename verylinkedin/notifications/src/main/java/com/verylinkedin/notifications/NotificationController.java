package com.verylinkedin.notifications;

import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class NotificationController {

    @RabbitListener(queues = "${rabbitmq.queues.notifications}")
    public static void SendNotificationToUser(String message,String userid){
        System.out.println("FEL NOTIFICATIONS");
        Notification notification=new Notification(message,userid,"user");
        AddNotification n=new AddNotification(notification);
        n.execute();
    }


    public static void SendNotificationToListOfUsers(String message,String[] ids){
        for(String id:ids){
            Notification notification=new Notification(message,id,"all");
            AddNotification n=new AddNotification(notification);
            n.execute();
        }

    }
}
