package com.verylinkedin.groupchat.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.CommandMap;
import com.verylinkedin.groupchat.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@Component
@AllArgsConstructor
@Slf4j

public class GroupsConsumer {

    private final GroupRepository groupRepository;


    @RabbitListener(queues = "${rabbitmq.queues.groups}")
    public Object consumer(String requestObject, Message requestFromQueue) throws JSONException, ParseException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JsonProcessingException {

            // This part uses reflection to dynamically process requests

            // Get the request type from the message properties
            String typeId = (String) requestFromQueue.getMessageProperties().getHeaders().get("__TypeId__");
            log.info("Consumed {} from queue {}", requestFromQueue, requestObject);
            log.info("Message of type {}", typeId);

            // Initialize the command map
            //new CommandMap();

            // Get classes for the request and the command using the request type header
            Class commandClass = CommandMap.getCommandClass(typeId);
            Class requestClass = CommandMap.getRequestClass(typeId);
            log.info("Maps look like this {} {}",commandClass.getName(), requestClass.getName());
            // Get the constructor for the command class
            Constructor commandConstructor = commandClass.getConstructor(requestClass, GroupRepository.class);

            // Now parse the request body
            JSONObject requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
            log.info("Request JSON looks like this {}", requestJSON);

            // We map the request to our request class
            ObjectMapper objectMapper = new ObjectMapper();
            Object mappedRequest = objectMapper.readValue(requestJSON.toString(), requestClass);
            log.info("Mapped object looks like this {}", mappedRequest);

            // Create the command using the mapped request and the repository
            Command commandObject = (Command) commandConstructor.newInstance(mappedRequest, groupRepository);
            Object response = commandObject.execute();
            log.info("Executed the command with response {}",response);
            return response;

        /*
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

        return null;*/
    }
}