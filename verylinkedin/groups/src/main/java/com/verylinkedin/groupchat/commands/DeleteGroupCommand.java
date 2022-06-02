package com.verylinkedin.groupchat.commands;

import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.classes.GroupChat;
import com.verylinkedin.groupchat.requests.DeleteGroupRequest;

public record DeleteGroupCommand(DeleteGroupRequest request, GroupRepository groupRepository) implements Command {

    @Override
    public Object execute() {
        // Query the database for GroupChats of the same ID
        GroupChat chat = groupRepository.findById(request.groupId()).get(0);
        // Check whether the user is the admin
        if (chat.getAdmin().equals(request.userId())){
            // Delete the chat
            groupRepository.delete(chat);
            return "Group deleted!";
        }
        return "Couldn't delete!";
    }
}
