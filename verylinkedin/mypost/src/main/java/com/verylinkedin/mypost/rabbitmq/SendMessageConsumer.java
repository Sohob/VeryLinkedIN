package com.verylinkedin.mypost.rabbitmq;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest ;

import java.io.IOException;
import java.util.ArrayList;


@Service
@AllArgsConstructor
@Slf4j
public class SendMessageConsumer {



    @RabbitListener(queues = "${rabbitmq.queues.groups}")
    public void consumer(Message requestFromQueue) throws JSONException {
//        String typeId = (String) requestFromQueue.getMessageProperties().getHeaders().get("__TypeId__");
        System.out.println("hahahahha");
        System.out.println(requestFromQueue.getMessageProperties().getHeaders().get("__TypeId__"));
        log.info("Consumed {} from queue", requestFromQueue);
        String typeId = (String) requestFromQueue.getMessageProperties().getHeaders().get("__TypeId__");
//        log.info("Message of type {}", typeId);
        JSONObject requestJSON;


        switch (typeId){
            case "com.verylinkedin.mypost.CreatePost.CreatePostRequest":

                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                CreatePostRequest x = new CreatePostRequest(requestJSON.getString("userId"),requestJSON.getString("content"));
                System.out.print(x);

        }




    }
}
