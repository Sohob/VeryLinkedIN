package com.verylinkedin.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class NotificationsApplication {

    public static void main(String[] args) throws IOException {
        CommandMap.getInstance();
        FirebaseInitialize.initialize();
        SpringApplication.run(NotificationsApplication.class, args);
        DatabaseListener listener=new DatabaseListener("user1");
    }
}
