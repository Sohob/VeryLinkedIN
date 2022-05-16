package com.verylinkedin.groupchat.rabbitmq;

import com.verylinkedin.groupchat.CommandMap;
import com.verylinkedin.groupchat.creategroup.CreateGroupRequest;
import com.verylinkedin.groupchat.GroupChatService;
import com.verylinkedin.groupchat.sendmessage.SendingMessageRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;


@Service
@AllArgsConstructor
@Slf4j
public class SendMessageConsumer {

    private final GroupChatService groupChatService;

    @RabbitListener(queues = "${rabbitmq.queues.groups}")
    public void consumer(Message requestFromQueue) throws JSONException {
        String typeId = (String) requestFromQueue.getMessageProperties().getHeaders().get("__TypeId__");
        log.info("Consumed {} from queue", requestFromQueue);
        log.info("Message of type {}", typeId);
        CommandMap cMap = new CommandMap();
        JSONObject requestJSON;
        switch (typeId){
            case "com.verylinkedin.groupchat.sendmessage.SendingMessageRequest":
                Class commandClass = cMap.getCommandMap().get(typeId);
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                log.info("Request JSON looks like this {}", requestJSON);
                SendingMessageRequest request = new SendingMessageRequest(requestJSON.getString("userId"),requestJSON.getString("groupId"),requestJSON.getString("message"));
                log.info("Request looks like this {}", request);
                groupChatService.sendMessage(request);break;
           case "com.verylinkedin.groupchat.creategroup.CreateGroupRequest":
               // Convert to a JSON object
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
               log.info("Request JSON looks like this {}", requestJSON.getJSONArray("participants").getString(0));

               // Convert participants JSON Array to ArrayList
               ArrayList<String> convertedArray = new ArrayList<>();
               JSONArray jsonArray = requestJSON.getJSONArray("participants");
               for(int i = 0;i < jsonArray.length();i++){
                   convertedArray.add(jsonArray.getString(i));
               }
               // Create the request
                CreateGroupRequest createGroupRequest = new CreateGroupRequest(
                        convertedArray,
                        requestJSON.getString("title"),
                        requestJSON.getString("description"),
                        requestJSON.getString("owner"),
                        requestJSON.getString("groupPhoto"));
                log.info("Sending {} to service", createGroupRequest);
                // Execute the request
               groupChatService.createGroup(createGroupRequest);
        }


    }
}