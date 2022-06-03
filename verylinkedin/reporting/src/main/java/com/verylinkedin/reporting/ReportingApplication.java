package com.verylinkedin.reporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ReportingApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(ReportingApplication.class, args);
    }
//    @GetMapping
//    public String[] hello(){
//        return new String[]{"Hello World"};
//    }

}
