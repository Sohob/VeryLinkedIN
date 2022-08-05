package com.verylinkedin.groupchat.commands;

import com.google.gson.Gson;
import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.classes.GroupChat;
import com.verylinkedin.groupchat.classes.Message;
import com.verylinkedin.groupchat.requests.DeleteMessageRequest;
import com.verylinkedin.groupchat.responses.Response;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public record DeleteMessageCommand(DeleteMessageRequest request, GroupRepository groupRepository) implements Command {
    @Override
    public Object execute() {
            // Query the database for GroupChats of the same ID
            GroupChat chat = groupRepository.findById(request.groupId()).get(0);
            // Get the chat's messages
            ArrayList<Message> messages = chat.getChatText();
            // Delete the message using the ID in the request
            messages.remove(Integer.parseInt(request.messageId()));
            // Update the group chat in the database
            groupRepository.save(chat);
            Gson gson = new Gson();
            return gson.toJson(new Response(chat.toString(), HttpStatus.OK));
    }
}
