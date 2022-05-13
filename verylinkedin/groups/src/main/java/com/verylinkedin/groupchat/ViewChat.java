package com.verylinkedin.groupchat;

public record ViewChat(GroupRepository groupRepository) implements Command{
    @Override
    public void execute() {

    }
}
