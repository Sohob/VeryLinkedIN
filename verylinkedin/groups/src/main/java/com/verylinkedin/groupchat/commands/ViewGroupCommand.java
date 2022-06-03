package com.verylinkedin.groupchat.commands;

import com.google.gson.Gson;
import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.classes.GroupChat;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.classes.Message;
import com.verylinkedin.groupchat.requests.ViewGroupRequest;
import com.verylinkedin.groupchat.responses.Response;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public record ViewGroupCommand(ViewGroupRequest request, GroupRepository groupRepository) implements Command {
    @Override
    public Object execute() {
        // Query the database for GroupChats of the same ID
        GroupChat chat = groupRepository.findById(request.groupId()).get(0);
        // Get the chat's messages
        ArrayList<Message> messages = chat.getChatText();
        // Loop over the messages array and let the user read every message sent so far in the chat
        for(Message message : messages){
            // Add the user to the read list
            if(!message.getRead().contains(request.userId()))
                message.getRead().add(request.userId());
            // Remove the user from the unread list
            message.getUnread().remove(request.userId());
        }
        // Update the group chat in the database
        groupRepository.save(chat);
        Gson gson = new Gson();
        return gson.toJson(new Response(chat.toString(), HttpStatus.OK));
    }
}
