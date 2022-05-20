package com.verylinkedin.groupchat.sendmessage;

import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupChat;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record SendMessage(SendingMessageRequest request, GroupRepository groupRepository) implements Command {

    @Override
    public Object execute() {
        GroupChat chat = groupRepository.findById(request.groupId()).get(0);
        ArrayList<String> unreadList = new ArrayList<String>(chat.getParticipants());
        ArrayList<String> readList = new ArrayList<String>();
        unreadList.remove(request.userId());
        readList.add(request.userId());
        Message message = Message.builder()
                .sender(request.userId())
                .content(request.message())
                .read(readList)
                .unread(unreadList)
                .time(LocalDateTime.now()).build();
        chat.getChatText().add(message);
        //groupRepository.deleteAll();
        groupRepository.save(chat);
        return null;
    }
}
