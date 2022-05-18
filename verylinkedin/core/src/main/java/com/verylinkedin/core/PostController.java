package com.verylinkedin.core;

import com.verylinkedin.amqp.RabbitMQMessageProducer;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest;
import com.verylinkedin.mypost.EditPost.EditPostRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/editPost")
    public void editPost(@RequestBody EditPostRequest editPostRequest)  {

        rabbitMQMessageProducer.publish(
                editPostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
    }

}
