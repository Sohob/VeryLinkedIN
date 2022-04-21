package com.verylinkedin.groupchat;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public record GroupChatService() {
    public void sendMessage(SendingMessageRequest request) {

    }

    public void createGroup(CreateGroupRequest request) {
        GroupChat groupChat = GroupChat.builder()
                .participants(request.participants())
                .admins(new ArrayList<Integer>(request.owner()))
                .title(request.title())
                .description(request.description())
                .groupPhoto(request.groupPhoto())
                .build();
    }

    public String viewChat(String groupId) {
        //TODO Check the DB for the groupChat and return the chatText
        return "viewing group " + groupId;
    }
    //TODO Link to the DB
}
