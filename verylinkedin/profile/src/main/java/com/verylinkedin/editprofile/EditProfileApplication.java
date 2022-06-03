package com.verylinkedin.editprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import java.io.IOException;

@SpringBootApplication
public class EditProfileApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(EditProfileApplication.class, args);
      }
}

