package com.verylinkedin.groupchat.viewchat;

import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupChat;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public record ViewChat(ViewChatRequest viewChatRequest, GroupRepository groupRepository) implements Command {
    @Override
    public Object execute() {
        // Query the database for GroupChats of the same ID
        GroupChat chat = groupRepository.findById(viewChatRequest.groupId()).get(0);
        // Get the chat's messages
        ArrayList<Message> messages = chat.getChatText();
        // Loop over the messages array and let the user read every message sent so far in the chat
        for(Message message : messages){
            // Add the user to the read list
            if(!message.getRead().contains(viewChatRequest.userId()))
                message.getRead().add(viewChatRequest.userId());
            // Remove the user from the unread list
            message.getUnread().remove(viewChatRequest.userId());
        }
        // Update the group chat in the database
        groupRepository.save(chat);
        return new ViewChatResponse(groupRepository.findById(viewChatRequest.groupId()).get(0).toString());
    }
}
