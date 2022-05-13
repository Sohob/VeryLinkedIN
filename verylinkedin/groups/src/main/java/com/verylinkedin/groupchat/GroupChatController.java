package com.verylinkedin.groupchat;

/*
import lombok.extern.slf4j.Slf4j;
import messagingrabbitmq.MessagingRabbitmqApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Component
@RequestMapping("api/v1/groups")
public record GroupChatController(GroupChatService groupChatService,Receiver receiver, RabbitTemplate rabbitTemplate) {


    @GetMapping("/view/{groupId}")
    public @ResponseBody
    ResponseEntity<String> viewChat(@PathVariable String groupId) throws InterruptedException {
        log.info("viewing messages in group {}", groupId);
        List res = groupChatService.viewChat(groupId);
        return new ResponseEntity<String>("Chat details: " + res,
                HttpStatus.OK);
    }/*
    @PostMapping("/view/{groupId}/send")
    public void sendMessage(@RequestBody SendingMessageRequest sendingMessageRequest){
        log.info("message being sent {}", sendingMessageRequest);
        groupChatService.sendMessage(sendingMessageRequest);
    }
    @PostMapping("/view/{groupId}/send")
    public void sendMessage(@RequestBody String body) throws InterruptedException, JSONException {
        JSONObject obj = new JSONObject(body);
        System.out.println("Sending message..."+obj.getString("message"));
        rabbitTemplate.convertAndSend(GroupChatApplication.topicExchangeName, "foo.bar.baz", body);
        rabbitTemplate.convertAndSend(GroupChatApplication.topicExchangeName2, "foo.far.baz", body);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
    @PostMapping("/create")
    public void createGroup(@RequestBody CreateGroupRequest createGroupRequest){
        log.info("message being sent {}", createGroupRequest);
        groupChatService.createGroup(createGroupRequest);
    }
}*/
