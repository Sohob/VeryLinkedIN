package com.verylinkedin.core;

import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
import com.verylinkedin.core.requests.CreateGroupRequest;
import com.verylinkedin.core.requests.Notification;
import com.verylinkedin.core.requests.NotificationList;
import com.verylinkedin.core.requests.SendingMessageRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@Component
@RequestMapping("api/v1/groups")
@Service
@AllArgsConstructor
public class Controller {
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @PostMapping("/notification_user")
    public String notification(@RequestBody Notification notification) {
        rabbitMQMessageProducer.publish(
                notification,
                "internal.exchange",
                "internal.notifications.routing.key"
        );
        return "NOTIFICATION";
    }
    @PostMapping("/notifications")
    public String notificationList(@RequestBody NotificationList notificationList) {
        rabbitMQMessageProducer.publish(
                notificationList,
                "internal.exchange",
                "internal.notifications.routing.key"
        );
        return "NOTIFICATION";
    }

    // TODO Replace userId from path variable when authentication is finished
    @PostMapping("/view/{userId}/{groupId}/send")
    public void sendMessage(@RequestBody SendingMessageRequest request, @PathVariable String userId, @PathVariable String groupId) {
        rabbitMQMessageProducer.publish(
                new SendingMessageRequest(userId,groupId,request.message()),
                "internal.exchange",
                "internal.groups.routing.key"
        );
    }
    @PostMapping("/create")
    public void createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        //log.info("message being sent {}", createGroupRequest);
        rabbitMQMessageProducer.publish(
                createGroupRequest,
                "internal.exchange",
                "internal.groups.routing.key"
        );
    }
        // TODO Replace userId from path variable when authentication is finished
    @GetMapping("/view/{userId}/{groupId}")
    public ResponseEntity<String> viewChat(@PathVariable String userId, @PathVariable String groupId) {
        log.info("viewing messages in group {} as user {}", groupId, userId);
        String res = rabbitMQMessageProducer.publishAndReceive(
                groupId,
                "internal.exchange",
                "internal.groups.routing.key"
        );
        return new ResponseEntity<String>("Chat details: " + res,
                HttpStatus.OK);
    }

}
