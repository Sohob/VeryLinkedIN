package com.verylinkedin.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.verylinkedin.core",
                "com.verylinkedin.amqp",
                "com.verylinkedin.groups",
                "com.verylinkedin.mypost",
                "com.verylinkedin.notifications"
        }
)
public class VerylinkedinApplication {


    public static void main(String[] args) {SpringApplication.run(VerylinkedinApplication.class, args);}
}

