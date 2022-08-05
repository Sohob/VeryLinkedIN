package com.verylinkedin.groupchat.commands;

import com.google.gson.Gson;
import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.classes.GroupChat;
import com.verylinkedin.groupchat.requests.UpdateGroupRequest;
import com.verylinkedin.groupchat.responses.Response;
import org.springframework.http.HttpStatus;

public record UpdateGroupCommand(UpdateGroupRequest request, GroupRepository groupRepository) implements Command {
    @Override
    public Object execute() {
        Gson gson = new Gson();
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
            return gson.toJson(new Response(chat.toString(), HttpStatus.OK));
        }
        return gson.toJson(new Response(chat.toString(), HttpStatus.METHOD_NOT_ALLOWED));
    }
}
