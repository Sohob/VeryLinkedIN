package com.verylinkedin.groupchat.commands;

import com.google.gson.Gson;
import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.classes.GroupChat;
import com.verylinkedin.groupchat.classes.Message;
import com.verylinkedin.groupchat.requests.CreateGroupRequest;
import com.verylinkedin.groupchat.responses.Response;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record CreateGroupCommand(CreateGroupRequest request,
                                 GroupRepository groupRepository) implements Command {

    @Override
    public Object execute() {
        Message messages = Message.builder()
                .sender(request.admin())
                .content("Chat has been created!")
                .read(new ArrayList<String>())
                .unread(request.participants())
                .time(LocalDateTime.now())
                .build();
        ArrayList<Message> tempList = new ArrayList<Message>();
        tempList.add(messages);
        GroupChat groupChat = GroupChat.builder()
                .participants(request.participants())
                .admin(request.admin())
                .title(request.title())
                .description(request.description())
                .groupPhoto(request.groupPhoto())
                .chatText(tempList)
                .build();
        groupRepository.save(groupChat);
        Gson gson = new Gson();
        return gson.toJson(new Response(groupChat.toString(), HttpStatus.OK));
    }
}
