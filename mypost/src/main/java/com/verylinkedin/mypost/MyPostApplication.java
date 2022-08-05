package com.verylinkedin.mypost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MyPostApplication {
    public static void main(String[] args) throws IOException {
        new CommandMap();
        SpringApplication.run(MyPostApplication.class, args);
    }
}

