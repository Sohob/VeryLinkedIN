package com.verylinkedin.core;

import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
import com.verylinkedin.core.requests.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
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

    @PostMapping("/user")
    public String notification(@RequestBody AddNotification addNotification) throws JSONException {
        rabbitMQMessageProducer.publishAndReceive(
                addNotification,
                "internal.exchange",
                "internal.notifications.routing.key"
        );
        return "USER";
    }
    @PostMapping("/list")
    public String notificationList(@RequestBody AddNotificationList addNotificationList) throws JSONException {
        rabbitMQMessageProducer.publishAndReceive(
                addNotificationList,
                "internal.exchange",
                "internal.notifications.routing.key"
        );
        return "LIST";
    }
}
