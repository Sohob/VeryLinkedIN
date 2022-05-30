package com.verylinkedin.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.verylinkedin.core.amqp.RabbitMQMessageProducer;
import com.verylinkedin.core.postsRequests.*;
import com.verylinkedin.core.postsResponse.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("")
    public ResponseEntity<String> getPosts(@RequestParam  String userId) throws JSONException, JsonProcessingException {
        log.info("viewing posts of user {}", userId);
        Object viewPostsResponse =  rabbitMQMessageProducer.publishAndReceive(
                new GetPostsRequest(userId),
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        log.info("here {}",viewPostsResponse );
        ResponseEntity<String> responseResponseEntity = new ResponseEntity<String>(String.valueOf(new Response(viewPostsResponse.toString())), HttpStatus.OK);
        return responseResponseEntity;

}}
