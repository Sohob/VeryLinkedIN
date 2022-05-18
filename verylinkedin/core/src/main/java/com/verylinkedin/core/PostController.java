package com.verylinkedin.core;

import com.verylinkedin.amqp.RabbitMQMessageProducer;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Component
@RequestMapping("api/v1/posts")
@Service
@AllArgsConstructor
public class PostController {
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @PostMapping("/createPost")
    public void  createPost(@RequestBody CreatePostRequest createPostRequest)  {

        rabbitMQMessageProducer.publish(
                createPostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
    }
}
