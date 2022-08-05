package com.verylinkedin.core;

import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
import com.verylinkedin.core.requests.AddNotification;
import com.verylinkedin.core.requests.AddNotificationList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
