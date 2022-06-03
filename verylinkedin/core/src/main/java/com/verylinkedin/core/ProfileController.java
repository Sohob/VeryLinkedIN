package com.verylinkedin.core;

import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
import com.verylinkedin.core.requests.CreateGroupRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Component
@RequestMapping("api/v1/profile")
@Service
@AllArgsConstructor


public class ProfileController {

    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    @PostMapping("/{userID}/EditProfile")
    public void createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        //log.info("message being sent {}", createGroupRequest);

    }
}