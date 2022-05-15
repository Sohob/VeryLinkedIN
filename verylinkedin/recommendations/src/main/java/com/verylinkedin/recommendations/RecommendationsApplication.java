package com.verylinkedin.recommendations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class RecommendationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecommendationsApplication.class, args);
    }

}
