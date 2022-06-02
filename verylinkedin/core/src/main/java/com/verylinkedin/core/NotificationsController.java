package com.verylinkedin.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
import com.verylinkedin.core.requests.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@Component
@RequestMapping("api/v1/notifications")
@Service
@AllArgsConstructor
public class NotificationsController {
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
}
