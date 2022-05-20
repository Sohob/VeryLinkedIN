package com.verylinkedin.core;

import com.verylinkedin.amqp.RabbitMQMessageProducer;
import com.verylinkedin.mypost.BanUser.BanUserRequest;
import com.verylinkedin.mypost.ChangeVisibility.ChangeVisibilityRequest;
import com.verylinkedin.mypost.AddComment.AddCommentRequest;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest;
import com.verylinkedin.mypost.EditPost.EditPostRequest;
import com.verylinkedin.mypost.LikePost.LikePostRequest;
import com.verylinkedin.mypost.UnlikePost.UnlikePostRequest;
import com.verylinkedin.mypost.deletePost.DeletePostRequest;
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
    public void editPost(@RequestBody EditPostRequest editPostRequest) {

        rabbitMQMessageProducer.publish(
                editPostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
    }
    @DeleteMapping("/deletePost")
    public void  deletePost(@RequestBody DeletePostRequest deletePostRequest)  {

        rabbitMQMessageProducer.publish(
                deletePostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
    }
    @PutMapping("/addComment")
    public void editPost(@RequestBody AddCommentRequest addCommentRequest) {

        rabbitMQMessageProducer.publish(
                addCommentRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
    }

    @PutMapping("/changeVisibility")
    public void  changeVisibility(@RequestBody ChangeVisibilityRequest changeVisibilityRequest)  {

        rabbitMQMessageProducer.publish(
                changeVisibilityRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
    }

    @PutMapping("/banUser")
    public void editPost(@RequestBody BanUserRequest banUserRequest) {

        rabbitMQMessageProducer.publish(
                banUserRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
    }

    @PutMapping("/likePost")
    public void  likePost(@RequestBody LikePostRequest likePostRequest)  {

        rabbitMQMessageProducer.publish(
                likePostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
    }

    @PutMapping("/unlikePost")
    public void unlikePost(@RequestBody UnlikePostRequest unlikePostRequest)  {

        rabbitMQMessageProducer.publish(
                unlikePostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
    }



}
