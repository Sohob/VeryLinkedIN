package com.verylinkedin.groupchat.commands;

import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.classes.GroupChat;
import com.verylinkedin.groupchat.classes.Message;
import com.verylinkedin.groupchat.requests.EditMessageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record EditMessageCommand(EditMessageRequest request, GroupRepository groupRepository) implements Command {
    @Override
    public Object execute() {
        try {
            // Query the database for GroupChats of the same ID
            GroupChat chat = groupRepository.findById(request.groupId()).get(0);

            // Get the chat's messages
            ArrayList<Message> messages = chat.getChatText();
            // Construct the edited message
            Message editedMessage = Message.builder()
                    .content(request.editedMessage())
                    .sender(request().userId())
                    .read(new ArrayList<>())
                    .unread(new ArrayList<>(chat.getParticipants()))
                    .time(LocalDateTime.now())
                    .build();
            // Change the message using the ID in the request
            // Validation check
            if(messages.get(Integer.parseInt(request.messageId())).getSender().equals(request.userId())) {
                messages.set(Integer.parseInt(request.messageId()), editedMessage);
                // Update the group chat in the database
                groupRepository.save(chat);
                return "Message edited: " + chat;
            }
            return "Cannot edit message!";
        }
        catch (Exception e){
            return e;
        }
    }
}
