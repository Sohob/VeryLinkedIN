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
@RequestMapping("api/v1/groups")
@Service
@AllArgsConstructor
public class GroupsController {
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
    public void sendMessage(@RequestBody SendMessageRequest request, @PathVariable String userId, @PathVariable String groupId) {
        try {
            rabbitMQMessageProducer.publish(
                    new SendMessageRequest(userId, groupId, request.message()),
                    "internal.exchange",
                    "internal.groups.routing.key"
            );
        }
        catch (Exception e){
            log.info(e.toString());
        }
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
    public ResponseEntity<String> viewChat(@PathVariable String userId, @PathVariable String groupId) throws JSONException, JsonProcessingException {
        log.info("viewing messages in group {} as user {}", groupId, userId);
        Object viewChatResponse = rabbitMQMessageProducer.publishAndReceive(
                new ViewChatRequest(userId,groupId),
                "internal.exchange",
                "internal.groups.routing.key"
        );
        return new ResponseEntity<String>("Chat details: " + viewChatResponse.toString(),
                HttpStatus.OK);

                /*
        try {
            ViewChatResponse res = listenableFuture.get();
            return new ResponseEntity<String>("Chat details: " + res,
                    HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<String>("Invalid chat",
                    HttpStatus.BAD_REQUEST);
        }*/

        /*
        AsyncRabbitTemplate.RabbitConverterFuture<ViewChatResponse> rabbitConverterFuture =  rabbitMQMessageProducer.publishAndReceive(
                new ViewChatRequest(userId,groupId),
                "internal.exchange",
                "internal.groups.routing.key"
        );
        rabbitConverterFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                // ...
            }

            @Override
            public void onSuccess(ViewChatResponse viewChatResponse) {
                log.info("Response received {}", viewChatResponse);

            }
        });*/
    }

}
