package com.verylinkedin.mypost.rabbitmq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.CommandMap;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.minio.config.MinioService;
import com.verylinkedin.mypost.models.Post;
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
import java.util.ArrayList;


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
        if (!isAuthorized(requestJSON, commandClass.getName())) {
            return "{\"message\":\"unauthorized action\"}";
        }

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

    public boolean isAuthorized(JSONObject requestJSON, String className) throws NoSuchMethodException {
        String curUserId = null;
        try {
            curUserId = (String) requestJSON.get("curUserId");
        } catch (Exception e) {

        }
        String postId = null;
        try {
            postId = (String) requestJSON.get("postId");
        } catch (Exception e) {

        }

        if (curUserId != null && postId == null) return true;
        if (className.contains("Like") || className.contains("Unlike") || className.contains("Comment") || className.contains("Recommend") || className.contains("CreateMediaRequest") || className.contains("GetPost")) {
            if (curUserId != null) {

                Post post = postRepository.findById(postId);
                String userId = post.getUserId();
                ArrayList<String> bannedList = post.getBanned();
                return (userId != null && userId.equals(curUserId)) || (bannedList != null && !bannedList.contains(curUserId));
            } else {
                return (className.contains("GetPost") && curUserId == null);
            }

        } else if (className.contains("Ban") || className.contains("Visibility") || className.contains("DeletePost") || className.contains("EditPost") || className.contains("CreateMedia")) {
            if (curUserId != null) {

                Post post = postRepository.findById(postId);
                String userId = post.getUserId();
                ArrayList<String> bannedList = post.getBanned();
                return (userId != null && userId.equals(curUserId));

            } else {
                return false;
            }

        } else return true;


    }


}