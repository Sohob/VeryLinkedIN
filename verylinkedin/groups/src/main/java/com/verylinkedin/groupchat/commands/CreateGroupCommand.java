package com.verylinkedin.groupchat.commands;

import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupChat;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.Message;
import com.verylinkedin.groupchat.requests.CreateGroupRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public final class CreateGroupCommand implements Command {
    private final CreateGroupRequest request;
    private final GroupRepository groupRepository;

    public CreateGroupCommand(CreateGroupRequest request, GroupRepository groupRepository) {
        this.request = request;
        this.groupRepository = groupRepository;
    }

    @Override
    public Object execute() {
        Message messages = Message.builder()
                .sender(request.owner())
                .content("Chat has been created!")
                .read(new ArrayList<String>())
                .unread(request.participants())
                .time(LocalDateTime.now())
                .build();
        ArrayList<Message> tempList = new ArrayList<Message>();
        tempList.add(messages);
        GroupChat groupChat = GroupChat.builder()
                .participants(request.participants())
                .admin(request.owner())
                .title(request.title())
                .description(request.description())
                .groupPhoto(request.groupPhoto())
                .chatText(tempList)
                .build();
        groupRepository.save(groupChat);
        return null;
    }

    public CreateGroupRequest request() {
        return request;
    }

    public GroupRepository groupRepository() {
        return groupRepository;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CreateGroupCommand) obj;
        return Objects.equals(this.request, that.request) &&
                Objects.equals(this.groupRepository, that.groupRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, groupRepository);
    }

    @Override
    public String toString() {
        return "CreateGroupCommand[" +
                "request=" + request + ", " +
                "groupRepository=" + groupRepository + ']';
    }

}
