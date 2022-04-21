package com.verylinkedin.groupchat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/groups")
public record GroupChatController(GroupChatService groupChatService) {

    @GetMapping("/view/{groupId}")
    public @ResponseBody
    ResponseEntity<String> viewChat(@PathVariable String groupId){
        log.info("viewing messages in group {}", groupId);
        String res = groupChatService.viewChat(groupId);
        return new ResponseEntity<String>("Chat details: " + res,
                HttpStatus.OK);
    }
    @PostMapping
    public void sendMessage(@RequestBody SendingMessageRequest sendingMessageRequest){
        log.info("message being sent {}", sendingMessageRequest);
        groupChatService.sendMessage(sendingMessageRequest);
    }
    @PostMapping("/create")
    public void createGroup(@RequestBody CreateGroupRequest createGroupRequest){
        log.info("message being sent {}", createGroupRequest);
        groupChatService.createGroup(createGroupRequest);
    }
}
