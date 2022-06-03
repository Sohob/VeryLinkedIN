package com.verylinkedin.mypost.rabbitmq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.CommandMap;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.minio.config.MinioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@Component
@Service
@AllArgsConstructor
@Slf4j

public class PostsConsumer {

    private final PostRepository postRepository;

//    private final MediaRepository mediaRepository;

    @Autowired
    private MinioService minioService;

    @RabbitListener(queues = "${rabbitmq.queues.groups}", concurrency = "${rabbitmq.consumers}-${rabbitmq.max-consumers}")
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
        log.info("Maps look like this {} {} {} ", commandClass.getName(), requestClass.getName(), (!requestClass.getName().equals("com.verylinkedin.mypost.commands.CreateMedia.CreateMedia")));
        // Get the constructor for the command class


        // Now parse the request body
        JSONObject requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
        log.info("Request JSON looks like this {}", requestJSON);

        // We map the request to our request class


        // Create the command using the mapped request and the repository
        Command commandObject;
        try {

            Constructor commandConstructor = commandClass.getConstructor(requestClass, PostRepository.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Object mappedRequest = objectMapper.readValue(requestJSON.toString(), requestClass);
            log.info("Mapped object looks like this {}", mappedRequest);
            commandObject = (Command) commandConstructor.newInstance(mappedRequest, postRepository);

        } catch (Exception e) {
            Constructor commandConstructor = commandClass.getConstructor(requestClass, MinioService.class, PostRepository.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Object mappedRequest = objectMapper.readValue(requestJSON.toString(), requestClass);
            log.info("Mapped object looks like this {}", mappedRequest);
            commandObject = (Command) commandConstructor.newInstance(mappedRequest, minioService, postRepository);
        }
        Object response = commandObject.execute();
        log.info("Executed the command with response {}", response);
        return response;

    }
}