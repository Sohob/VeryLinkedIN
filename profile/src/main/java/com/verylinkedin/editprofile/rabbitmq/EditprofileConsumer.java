package com.verylinkedin.editprofile.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verylinkedin.editprofile.CommandMap;
import com.verylinkedin.editprofile.UserRepository;
import com.verylinkedin.groupchat.Command;
import com.verylinkedin.groupchat.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@Component
@AllArgsConstructor
@Slf4j

public class EditprofileConsumer {

    private final UserRepository UserRepository;


    @RabbitListener(queues = "${rabbitmq.queues.editprofile}", concurrency = "${rabbitmq.consumers}-${rabbitmq.max-consumers}")
    public Object consumer(String requestObject, Message requestFromQueue) throws JSONException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, NoSuchFieldException {

        // This part uses reflection to dynamically process requests
        // Load for testing multithreading
        //BigInteger veryBig = new BigInteger(10000, new Random());
        //veryBig.nextProbablePrime();

        // Get the request type from the message properties
        String typeId = (String) requestFromQueue.getMessageProperties().getHeaders().get("__TypeId__");
        log.info("Consumed {} from queue {}", requestFromQueue, requestObject);
        log.info("Message of type {}", typeId);

        // Get classes for the request and the command using the request type header
        Class commandClass = CommandMap.getInstance().getCommandClass(typeId);
        Class requestClass = CommandMap.getInstance().getRequestClass(typeId);
        log.info("Maps look like this {} {}",commandClass.getName(), requestClass.getName());
        // Get the constructor for the command class
        Constructor commandConstructor = commandClass.getConstructor(requestClass, UserRepository.class);

        // Now parse the request body
        JSONObject requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
        log.info("Request JSON looks like this {}", requestJSON);

        // We map the request to our request class
        ObjectMapper objectMapper = new ObjectMapper();
        Object mappedRequest = objectMapper.readValue(requestJSON.toString(), requestClass);
        log.info("Mapped object looks like this {}", mappedRequest);

        // Create the command using the mapped request and the repository
        Command commandObject = (Command) commandConstructor.newInstance(mappedRequest, UserRepository);
        Object response = commandObject.execute();
        log.info("Executed the command with response {}",response);
        return response;
    }
}