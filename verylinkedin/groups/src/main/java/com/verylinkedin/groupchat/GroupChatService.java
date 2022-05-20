package com.verylinkedin.groupchat;

import com.verylinkedin.groupchat.creategroup.CreateGroup;
import com.verylinkedin.groupchat.creategroup.CreateGroupRequest;
import com.verylinkedin.groupchat.sendmessage.SendMessage;
import com.verylinkedin.groupchat.sendmessage.SendingMessageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public record GroupChatService(GroupRepository groupRepository) {
    public void sendMessage(SendingMessageRequest request) {
        /*List<GroupChat> chat = groupRepository.findAll();
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
        groupRepository.saveAll(chat);*/
        //JSONObject obj = new JSONObject(request);
        SendMessage sendMessage = new SendMessage(request, groupRepository);
        Thread thread = new Thread(new CommandRunnable(sendMessage));
        thread.start();
        //sendMessage.execute();
    }

    public void createGroup(CreateGroupRequest request) {
        /*Message messages = Message.builder()
                .sender(request.owner())
                .content("Chat has been created!")
                .read(new ArrayList<Integer>())
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
        groupRepository.save(groupChat);*/
        CreateGroup createGroup = new CreateGroup(request, groupRepository);
        //createGroup.execute();
        Thread thread = new Thread(new CommandRunnable(createGroup));
        thread.start();
    }

    public GroupChat viewChat(String groupId, String userId) {
        // Query the database for GroupChats of the same ID
        GroupChat chat = groupRepository.findById(groupId).get(0);
        // Get the chat's messages
        ArrayList<Message> messages = chat.getChatText();
        // Loop over the messages array and let the user read every message sent so far in the chat
        for(Message message : messages){
            // Add the user to the read list
            if(!message.getRead().contains(userId))
                message.getRead().add(userId);
            // Remove the user from the unread list
            message.getUnread().remove(userId);
        }
        // Update the group chat in the database
        groupRepository.save(chat);
        return groupRepository.findById(groupId).get(0);
    }
}
