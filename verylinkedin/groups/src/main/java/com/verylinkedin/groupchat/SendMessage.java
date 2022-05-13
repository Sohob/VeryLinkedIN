package com.verylinkedin.groupchat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record SendMessage(SendingMessageRequest request, GroupRepository groupRepository) implements Command{

    @Override
    public void execute() {
        List<GroupChat> chat = groupRepository.findAll();
        ArrayList<Integer> unreadList = new ArrayList<>(chat.get(0).getParticipants());
        ArrayList<Integer> readList = new ArrayList<>();
        unreadList.remove(request.sender());
        readList.add(request.sender());
        Message message = Message.builder()
                .sender(request.sender())
                .content(request.message())
                .read(readList)
                .unread(unreadList)
                .time(LocalDateTime.now()).build();
        chat.get(0).getChatText().add(message);
        groupRepository.deleteAll();
        groupRepository.saveAll(chat);
    }
}
