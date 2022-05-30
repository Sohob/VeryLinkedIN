package com.verylinkedin.groupchat;
/*/*
import com.verylinkedin.groupchat.commands.CreateGroupCommand;
import com.verylinkedin.groupchat.requests.CreateGroupRequest;
import com.verylinkedin.groupchat.commands.SendMessageCommand;
import com.verylinkedin.groupchat.requests.SendMessageRequest;
import com.verylinkedin.groupchat.commands.ViewChatCommand;
import com.verylinkedin.groupchat.requests.ViewChatRequest;
import com.verylinkedin.groupchat.responses.ViewChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record GroupChatService(GroupRepository groupRepository) {
    public void sendMessage(SendMessageRequest request) throws JSONException {
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
        // Parse the JSON request to form the body
/*
        SendMessageCommand sendMessage = new SendMessageCommand(request, groupRepository);
        //Thread thread = new Thread(new CommandRunnable(sendMessage));
        //thread.start();
        sendMessage.execute();
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
        groupRepository.save(groupChat);*//*
        CreateGroupCommand createGroup = new CreateGroupCommand(request, groupRepository);
        //createGroup.execute();
        Thread thread = new Thread(new CommandRunnable(createGroup));
        thread.start();
    }

    public ViewChatResponse viewChat(ViewChatRequest request) {
        /*

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
        return groupRepository.findById(groupId).get(0);*//*
        ViewChatCommand viewChat = new ViewChatCommand(request, groupRepository);
        //ViewChatResponse response = (ViewChatResponse) viewChat.execute();
        // TODO Multithreading here
        //Thread thread = new Thread(new CommandRunnable(createGroup));
        //thread.start();
        return null;
    }
}*/
