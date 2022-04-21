package com.verylinkedin.groupchat;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record GroupChatService(GroupRepository groupRepository) {
    public void sendMessage(SendingMessageRequest request) {
        List<GroupChat> chat = groupRepository.findAll();
        chat.get(0).setChatText(chat.get(0).getChatText() + request.message());
        groupRepository.deleteAll();
        groupRepository.saveAll(chat);
    }

    public void createGroup(CreateGroupRequest request) {
        GroupChat groupChat = GroupChat.builder()
                .participants(request.participants())
                .admins(new ArrayList<Integer>(request.owner()))
                .title(request.title())
                .description(request.description())
                .groupPhoto(request.groupPhoto())
                .chatText("Chat has been created!")
                .build();
        groupRepository.save(groupChat);
    }

    public List<GroupChat> viewChat(String groupId) {
        //TODO Check the DB for the groupChat and return the chatText

        return groupRepository.findAll();
    }
    //TODO Link to the DB
}
