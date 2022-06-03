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

    // TODO Replace userId from path variable when authentication is finished
    @PostMapping("/view/{userId}/{groupId}/send")
    public void sendMessage(@RequestBody SendMessageRequest sendMessageRequest,
                            @PathVariable String userId,
                            @PathVariable String groupId) {
        try {
            log.info("message being sent {}", sendMessageRequest);
            rabbitMQMessageProducer.publish(
                    new SendMessageRequest(userId, groupId, sendMessageRequest.message()),
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
        log.info("message being sent {}", createGroupRequest);
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
        Object response = rabbitMQMessageProducer.publishAndReceive(
                new ViewGroupRequest(userId,groupId),
                "internal.exchange",
                "internal.groups.routing.key"
        );
        return new ResponseEntity<String>("Chat details: " + response.toString(),
                HttpStatus.OK);
    }
    @DeleteMapping("/view/{userId}/{groupId}/deletemsg")
    public void deleteMessage(@RequestBody DeleteMessageRequest deleteMessageRequest,
                              @PathVariable String userId,
                              @PathVariable String groupId) {
        log.info("message being sent {}", deleteMessageRequest);
        rabbitMQMessageProducer.publish(
                new DeleteMessageRequest(userId, groupId, deleteMessageRequest.messageId()),
                "internal.exchange",
                "internal.groups.routing.key"
        );
    }
    @PutMapping("/view/{userId}/{groupId}/editmsg")
    public void editMessage(@RequestBody EditMessageRequest editMessageRequest,
                            @PathVariable String userId,
                            @PathVariable String groupId) {
        log.info("message being sent {}", editMessageRequest);
        rabbitMQMessageProducer.publish(
                new EditMessageRequest(
                        userId,
                        groupId,
                        editMessageRequest.messageId(),
                        editMessageRequest.editedMessage()
                ),
                "internal.exchange",
                "internal.groups.routing.key"
        );
    }
    @DeleteMapping("/view/{userId}/{groupId}/deletegroup")
    public void deleteGroup(@RequestBody DeleteGroupRequest deleteGroupRequest,
                            @PathVariable String userId,
                            @PathVariable String groupId) {
        log.info("message being sent {}", deleteGroupRequest);
        rabbitMQMessageProducer.publish(
                new DeleteGroupRequest(userId, groupId),
                "internal.exchange",
                "internal.groups.routing.key"
        );
    }
    @PutMapping("/view/{userId}/{groupId}/updategroup")
    public void updateGroup(@RequestBody UpdateGroupRequest updateGroupRequest,
                            @PathVariable String userId,
                            @PathVariable String groupId) {
        log.info("message being sent {}", updateGroupRequest);
        rabbitMQMessageProducer.publish(
                new UpdateGroupRequest(
                        userId,
                        groupId,
                        updateGroupRequest.participants(),
                        updateGroupRequest.title(),
                        updateGroupRequest.description(),
                        updateGroupRequest.admin(),
                        updateGroupRequest.groupPhoto()
                ),
                "internal.exchange",
                "internal.groups.routing.key"
        );
    }
}
