package com.verylinkedin.groupchat;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record CreateGroup(CreateGroupRequest request, GroupRepository groupRepository) implements Command{

    @Override
    public void execute() {
        Message messages = Message.builder()
                .sender(request.owner())
                .content("Chat has been created!")
                .read(new ArrayList<Integer>())
                .unread(request.participants())
                .time(LocalDateTime.now())
                .build();
        ArrayList<Message> tempList = new ArrayList<Message>();
        tempList.add(messages);
        GroupChat groupChat = GroupChat.builder()
                .participants(request.participants())
                .admin(request.owner())
                .title(request.title())
                .description(request.description())
                .groupPhoto(request.groupPhoto())
                .chatText(tempList)
                .build();
        groupRepository.save(groupChat);
    }
}
