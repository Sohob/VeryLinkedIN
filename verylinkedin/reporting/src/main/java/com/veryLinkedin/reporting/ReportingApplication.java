package com.veryLinkedin.reporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
public class ReportingApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(ReportingApplication.class, args);
        new CommandMap();
    }
//    @GetMapping
//    public String[] hello(){
//        return new String[]{"Hello World"};
//    }

}
