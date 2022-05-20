package com.verylinkedin.groupchat.rabbitmq;

import com.google.gson.Gson;
import com.verylinkedin.groupchat.CommandMap;
import com.verylinkedin.groupchat.creategroup.CreateGroupRequest;
import com.verylinkedin.groupchat.GroupChatService;
import com.verylinkedin.groupchat.sendmessage.SendingMessageRequest;
import com.verylinkedin.groupchat.viewchat.ViewChatRequest;
import com.verylinkedin.groupchat.viewchat.ViewChatResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@AllArgsConstructor
@Slf4j
public class GroupsConsumer {

    private final GroupChatService groupChatService;

    @RabbitListener(queues = "${rabbitmq.queues.groups}")
    public Object consumer(Message requestFromQueue) throws JSONException, ParseException {
        String typeId = (String) requestFromQueue.getMessageProperties().getHeaders().get("__TypeId__");
        log.info("Consumed {} from queue", requestFromQueue);
        log.info("Message of type {}", typeId);
        CommandMap cMap = new CommandMap();
        JSONObject requestJSON;
        switch (typeId){
            case "com.verylinkedin.core.requests.SendingMessageRequest":
                Class commandClass = cMap.getCommandMap().get(typeId);
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                log.info("Request JSON looks like this {}", requestJSON);
                SendingMessageRequest request = new SendingMessageRequest(
                        requestJSON.getString("userId"),
                        requestJSON.getString("groupId"),
                        requestJSON.getString("message"));
                log.info("Request looks like this {}", request);
                groupChatService.sendMessage(request);break;
           case "com.verylinkedin.core.requests.CreateGroupRequest":
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
               break;
            case "com.verylinkedin.core.requests.ViewChatRequest":
                // Convert to a JSON object
                //requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                log.info("Request JSON looks like this {}", requestJSON);


                // Create the request
                ViewChatRequest viewChatRequest = new ViewChatRequest(
                        requestJSON.getString("userId"),
                        requestJSON.getString("groupId"));
                log.info("Request looks like this {}", viewChatRequest);
                //log.info("Sending {} to service", createGroupRequest);
                // Execute the request
                ViewChatResponse viewChatResponse = groupChatService.viewChat(viewChatRequest);
                log.info("Returning response {}", viewChatResponse);
                MessageProperties messageProperties = new MessageProperties();
                messageProperties.setContentType(messageProperties.CONTENT_TYPE_JSON);
                Gson gson = new Gson();
                String viewChatResponseGson = gson.toJson(viewChatResponse.toString(), String.class);
                Message responseMessage = new Message(viewChatResponseGson.getBytes(), messageProperties);
                return responseMessage;
        }

        return null;
    }
}