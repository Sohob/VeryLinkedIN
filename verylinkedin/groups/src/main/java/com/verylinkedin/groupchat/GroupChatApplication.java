package com.verylinkedin.groupchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GroupChatApplication {

    public static void main(String[] args) throws IOException {
        new CommandMap();
        SpringApplication.run(GroupChatApplication.class, args);
    }
}
