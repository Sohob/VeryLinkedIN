package com.verylinkedin.groupchat;

import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public record GroupChatService(GroupRepository groupRepository, MessageRepository messageRepository) {
    @RabbitListener(queues = "#{sendMessage.name}")
    public void sendMessage(String request) throws JSONException {
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
        JSONObject obj = new JSONObject(request);
        SendMessage sendMessage = new SendMessage(new SendingMessageRequest(obj.getInt("senderID"), obj.getString("message")), groupRepository);
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
        groupRepository.save(groupChat);*/
        CreateGroup createGroup = new CreateGroup(request, groupRepository);
        createGroup.execute();
    }

    public List<GroupChat> viewChat(String groupId) {
        return groupRepository.findAll();
        //TODO Check the DB for the groupChat and return the chatText
        //return groupRepository.findOne(eq("_id", new ObjectId(groupId)));
    }
}
