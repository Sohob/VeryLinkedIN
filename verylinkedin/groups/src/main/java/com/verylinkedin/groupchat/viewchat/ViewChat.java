package com.verylinkedin.groupchat.viewchat;

import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record ViewChat(GroupRepository groupRepository) implements Command {
    @Override
    public void execute() {


    }
}
