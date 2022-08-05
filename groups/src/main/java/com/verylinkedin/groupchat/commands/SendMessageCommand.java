package com.verylinkedin.groupchat.commands;

import com.google.gson.Gson;
import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.classes.GroupChat;
import com.verylinkedin.groupchat.classes.Message;
import com.verylinkedin.groupchat.requests.SendMessageRequest;
import com.verylinkedin.groupchat.responses.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record SendMessageCommand(SendMessageRequest request, GroupRepository groupRepository) implements Command {

    @Override
    public Object execute() throws JSONException {
        GroupChat chat = groupRepository.findById(request.groupId()).get(0);
        ArrayList<String> unreadList = new ArrayList<String>(chat.getParticipants());
        ArrayList<String> readList = new ArrayList<String>();
        unreadList.remove(request.userId());
        readList.add(request.userId());
        Message message = Message.builder()
                .sender(request.userId())
                .content(request.message())
                .read(readList)
                .unread(unreadList)
                .time(LocalDateTime.now()).build();
        chat.getChatText().add(message);
        groupRepository.save(chat);
        Gson gson = new Gson();
        return gson.toJson(new Response(chat.toString(), HttpStatus.OK));
    }
}
