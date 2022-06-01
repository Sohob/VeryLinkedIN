package com.verylinkedin.groupchat.commands;

import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.classes.GroupChat;
import com.verylinkedin.groupchat.classes.Message;
import com.verylinkedin.groupchat.requests.UpdateGroupRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record UpdateGroupCommand(UpdateGroupRequest request, GroupRepository groupRepository) implements Command {
    @Override
    public Object execute() {
        // Query the database for GroupChats of the same ID
        GroupChat chat = groupRepository.findById(request.groupId()).get(0);
        if(chat.getAdmin().equals(request.userId())) {
            // Change the chat details using the given data in the body
            chat.setParticipants(request.participants());
            chat.setTitle(request.title());
            chat.setAdmin(request.admin());
            chat.setDescription(request.description());
            chat.setGroupPhoto(request.groupPhoto());
            // Update the group chat in the database
            groupRepository.save(chat);
            return "Chat updated: " + chat;
        }
        return "Couldn't update";
    }
}
