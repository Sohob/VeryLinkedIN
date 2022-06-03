package com.verylinkedin.groupchat.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.CommandMap;
import com.verylinkedin.groupchat.GroupRepository;
import com.verylinkedin.groupchat.responses.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@Component
@AllArgsConstructor
@Slf4j

public class GroupsConsumer {

    private final GroupRepository groupRepository;


    @RabbitListener(queues = "${rabbitmq.queues.groups}", concurrency = "${rabbitmq.consumers}-${rabbitmq.max-consumers}",
    returnExceptions = "true")
    public Object consumer(Message requestFromQueue) {
            try {
                    // This part uses reflection to dynamically process requests
                    // Load for testing multithreading
                    //BigInteger veryBig = new BigInteger(10000, new Random());
                    //veryBig.nextProbablePrime();

                    // Get the request type from the message properties
                    String typeId = (String) requestFromQueue.getMessageProperties().getHeaders().get("__TypeId__");
                    log.info("Consumed {} from queue {}", requestFromQueue, "yo");
                    log.info("Message of type {}", typeId);

                    // Get classes for the request and the command using the request type header
                    Class commandClass = CommandMap.getInstance().getCommandClass(typeId);
                    Class requestClass = CommandMap.getInstance().getRequestClass(typeId);
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

                    //Creating the ObjectMapper object
                    //ObjectMapper mapper = new ObjectMapper();
                    //Converting the Object to JSONString
                    //String jsonString = mapper.writeValueAsString(new ResponseEntity<String>((String) response, HttpStatus.OK));
                    //System.out.println(jsonString);
                    log.info("Executed the command with response {}", response);
                    MessageProperties messageProperties = new MessageProperties();
                    messageProperties.setContentType("json");
                    return response;
                    //return new Message(jsonString.getBytes(), messageProperties);
            }
            catch (Exception e) {
                    return new Response(e.toString(), HttpStatus.BAD_REQUEST);
            }
    }
}