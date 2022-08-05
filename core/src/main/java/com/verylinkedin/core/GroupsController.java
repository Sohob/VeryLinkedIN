package com.verylinkedin.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
import com.verylinkedin.core.requests.*;
import com.verylinkedin.core.responses.Response;
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
public class GroupsController {
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    // TODO Replace userId from path variable when authentication is finished
    @PostMapping("/view/{userId}/{groupId}/send")
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageRequest sendMessageRequest,
                                              @PathVariable String userId,
                                              @PathVariable String groupId) {
        log.info("message being sent {}", sendMessageRequest);
        byte[] response = (byte[]) rabbitMQMessageProducer.publishAndReceive(
                new SendMessageRequest(userId, groupId, sendMessageRequest.message()),
                "internal.exchange",
                "internal.groups.routing.key"
        );
        log.info("Response received {}", new String(response));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response mappedResponse = objectMapper.readValue(new String(response), Response.class);
            return new ResponseEntity<String>(mappedResponse.body(), mappedResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        log.info("message being sent {}", createGroupRequest);
        byte[] response = (byte[]) rabbitMQMessageProducer.publishAndReceive(
                createGroupRequest,
                "internal.exchange",
                "internal.groups.routing.key"
        );
        log.info("Response received {}", new String(response));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response mappedResponse = objectMapper.readValue(new String(response), Response.class);
            return new ResponseEntity<String>(mappedResponse.body(), mappedResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    // TODO Replace userId from path variable when authentication is finished
    @GetMapping("/view/{userId}/{groupId}")
    public ResponseEntity<String> viewChat(@PathVariable String userId, @PathVariable String groupId) {
        log.info("viewing messages in group {} as user {}", groupId, userId);
        byte[] response = (byte[]) rabbitMQMessageProducer.publishAndReceive(
                new ViewGroupRequest(userId, groupId),
                "internal.exchange",
                "internal.groups.routing.key"
        );
        log.info("Response received {}", new String(response));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response mappedResponse = objectMapper.readValue(new String(response), Response.class);
            return new ResponseEntity<String>(mappedResponse.body(), mappedResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/view/{userId}/{groupId}/deletemsg")
    public ResponseEntity<String> deleteMessage(@RequestBody DeleteMessageRequest deleteMessageRequest,
                                                @PathVariable String userId,
                                                @PathVariable String groupId) {
        log.info("message being sent {}", deleteMessageRequest);
        byte[] response = (byte[]) rabbitMQMessageProducer.publishAndReceive(
                new DeleteMessageRequest(userId, groupId, deleteMessageRequest.messageId()),
                "internal.exchange",
                "internal.groups.routing.key"
        );
        log.info("Response received {}", new String(response));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response mappedResponse = objectMapper.readValue(new String(response), Response.class);
            return new ResponseEntity<String>(mappedResponse.body(), mappedResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/view/{userId}/{groupId}/editmsg")
    public ResponseEntity<String> editMessage(@RequestBody EditMessageRequest editMessageRequest,
                                              @PathVariable String userId,
                                              @PathVariable String groupId) {
        log.info("message being sent {}", editMessageRequest);
        byte[] response = (byte[]) rabbitMQMessageProducer.publishAndReceive(
                new EditMessageRequest(
                        userId,
                        groupId,
                        editMessageRequest.messageId(),
                        editMessageRequest.editedMessage()
                ),
                "internal.exchange",
                "internal.groups.routing.key"
        );
        log.info("Response received {}", new String(response));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response mappedResponse = objectMapper.readValue(new String(response), Response.class);
            return new ResponseEntity<String>(mappedResponse.body(), mappedResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/view/{userId}/{groupId}/deletegroup")
    public ResponseEntity<String> deleteGroup(@RequestBody DeleteGroupRequest deleteGroupRequest,
                                              @PathVariable String userId,
                                              @PathVariable String groupId) {
        log.info("message being sent {}", deleteGroupRequest);
        byte[] response = (byte[]) rabbitMQMessageProducer.publishAndReceive(
                new DeleteGroupRequest(userId, groupId),
                "internal.exchange",
                "internal.groups.routing.key"
        );
        log.info("Response received {}", new String(response));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response mappedResponse = objectMapper.readValue(new String(response), Response.class);
            return new ResponseEntity<String>(mappedResponse.body(), mappedResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/view/{userId}/{groupId}/updategroup")
    public ResponseEntity<String> updateGroup(@RequestBody UpdateGroupRequest updateGroupRequest,
                                              @PathVariable String userId,
                                              @PathVariable String groupId) {
        log.info("message being sent {}", updateGroupRequest);
        byte[] response = (byte[]) rabbitMQMessageProducer.publishAndReceive(
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
        log.info("Response received {}", new String(response));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response mappedResponse = objectMapper.readValue(new String(response), Response.class);
            return new ResponseEntity<String>(mappedResponse.body(), mappedResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
