package com.verylinkedin.core;
import com.verylinkedin.amqp.RabbitMQMessageProducer;
import com.verylinkedin.core.requests.CreateGroupRequest;
import com.verylinkedin.core.requests.SendingMessageRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    // TODO Replace userId from path variable when authentication is finished
    @PostMapping("/view/{userId}/{groupId}/send")
    public void sendMessage(@RequestBody SendingMessageRequest request, @PathVariable String userId, @PathVariable String groupId){
        rabbitMQMessageProducer.publish(
                new SendingMessageRequest(userId,groupId,request.message()),
                "internal.exchange",
                "internal.groups.routing.key"
        );
    }
    @PostMapping("/create")
    public void createGroup(@RequestBody CreateGroupRequest createGroupRequest){
        //log.info("message being sent {}", createGroupRequest);
        rabbitMQMessageProducer.publish(
                createGroupRequest,
                "internal.exchange",
                "internal.groups.routing.key"
        );
    }/*
        // TODO Replace userId from path variable when authentication is finished
    @GetMapping("/view/{groupId}")
    public void viewChat(@PathVariable String groupId) throws InterruptedException {
        log.info("viewing messages in group {}", groupId);
        rabbitMQMessageProducer.publish(
                groupId,
                "internal.exchange",
                "internal.groups.routing.key"
        );
    }*/
    //@RabbitListener(queues = "${rabbitmq.queues.groups}")
    public @ResponseBody
    ResponseEntity<String> viewChatResponse(ResponseEntity<String> message) {
        return message;
    }
}
