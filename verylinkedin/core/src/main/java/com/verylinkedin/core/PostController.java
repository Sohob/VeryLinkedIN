package com.verylinkedin.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.verylinkedin.core.amqp.RabbitMQMessageProducerV2;
import com.verylinkedin.core.postsRequests.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Component
@RequestMapping("api/v1/posts")
@Service
@AllArgsConstructor
public class PostController {
    private final RabbitMQMessageProducerV2 rabbitMQMessageProducer;

    @PostMapping("/createPost")
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequest createPostRequest) {

        byte[] result = rabbitMQMessageProducer.publishAndReceive(
                createPostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        ResponseEntity<String> responseResponseEntity = new ResponseEntity<String>(new String(result), HttpStatus.OK);
        return responseResponseEntity;
    }

    @PutMapping("/editPost")
    public ResponseEntity<String> editPost(@RequestBody EditPostRequest editPostRequest) {

        byte[] result = rabbitMQMessageProducer.publishAndReceive(
                editPostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        ResponseEntity<String> responseResponseEntity = new ResponseEntity<String>(new String(result), HttpStatus.OK);
        return responseResponseEntity;
    }

    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestBody DeletePostRequest deletePostRequest) {

        byte[] result = rabbitMQMessageProducer.publishAndReceive(
                deletePostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        ResponseEntity<String> responseResponseEntity = new ResponseEntity<String>(new String(result), HttpStatus.OK);
        return responseResponseEntity;
    }

    @PutMapping("/addComment")
    public ResponseEntity<String> editPost(@RequestBody AddCommentRequest addCommentRequest) {

        byte[] result = rabbitMQMessageProducer.publishAndReceive(
                addCommentRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        ResponseEntity<String> responseResponseEntity = new ResponseEntity<String>(new String(result), HttpStatus.OK);
        return responseResponseEntity;
    }

    @PutMapping("/changeVisibility")
    public ResponseEntity<String> changeVisibility(@RequestBody ChangeVisibilityRequest changeVisibilityRequest) {

        byte[] result = rabbitMQMessageProducer.publishAndReceive(
                changeVisibilityRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        ResponseEntity<String> responseResponseEntity = new ResponseEntity<String>(new String(result), HttpStatus.OK);
        return responseResponseEntity;
    }

    @PutMapping("/banUser")
    public ResponseEntity<String> editPost(@RequestBody BanUserRequest banUserRequest) {

        byte[] result = rabbitMQMessageProducer.publishAndReceive(
                banUserRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        ResponseEntity<String> responseResponseEntity = new ResponseEntity<String>(new String(result), HttpStatus.OK);
        return responseResponseEntity;
    }

    @PutMapping("/likePost")
    public ResponseEntity<String> likePost(@RequestBody LikePostRequest likePostRequest) {

        byte[] result = rabbitMQMessageProducer.publishAndReceive(
                likePostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        ResponseEntity<String> responseResponseEntity = new ResponseEntity<String>(new String(result), HttpStatus.OK);
        return responseResponseEntity;
    }

    @PutMapping("/unlikePost")
    public ResponseEntity<String> unlikePost(@RequestBody UnlikePostRequest unlikePostRequest) {

        byte[] result = rabbitMQMessageProducer.publishAndReceive(
                unlikePostRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        ResponseEntity<String> responseResponseEntity = new ResponseEntity<String>(new String(result), HttpStatus.OK);
        return responseResponseEntity;
    }

    @GetMapping("/{object}")
    public ResponseEntity<String> getPost(@PathVariable("object") String object) throws JSONException, JsonProcessingException {
        byte[] viewPostsResponse = rabbitMQMessageProducer.publishAndReceive(
                new GetPostRequest(object),
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        log.info("here {}", new String(viewPostsResponse));
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new Gson();

        map = (Map<String, Object>) gson.fromJson("{\"result\":" + new String(viewPostsResponse) + "}", map.getClass());

        ResponseEntity<String> responseResponseEntity = new ResponseEntity(map, HttpStatus.OK);
        return responseResponseEntity;
    }

    @GetMapping("")
    public ResponseEntity<String> getPosts(@RequestParam(required = true) String userId) throws JSONException, JsonProcessingException {
        log.info("viewing posts of user {}", userId);
        byte[] viewPostsResponse = rabbitMQMessageProducer.publishAndReceive(
                new GetPostsRequest(userId),
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        log.info("here {}", new String(viewPostsResponse));
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new Gson();

        map = (Map<String, Object>) gson.fromJson("{\"result\":" + new String(viewPostsResponse) + "}", map.getClass());

        ResponseEntity<String> responseResponseEntity = new ResponseEntity(map, HttpStatus.OK);
        return responseResponseEntity;
    }

    @GetMapping("/recommend")
    public ResponseEntity<String> recommendPosts(@RequestParam(required = true) String label) throws JSONException, JsonProcessingException {
        byte[] viewPostsResponse = rabbitMQMessageProducer.publishAndReceive(
                new RecommendPostsRequest(label),
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        log.info("here {}", new String(viewPostsResponse));
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new Gson();

        map = (Map<String, Object>) gson.fromJson("{\"result\":" + new String(viewPostsResponse) + "}", map.getClass());

        ResponseEntity<String> responseResponseEntity = new ResponseEntity(map, HttpStatus.OK);
        return responseResponseEntity;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadAttachement(@RequestParam("file") MultipartFile file, @RequestParam(required = true) String postId, HttpServletRequest request) throws IOException {


        CreateMediaRequest createMediaRequest = new CreateMediaRequest(file.getBytes(), file.getOriginalFilename(), file.getContentType(), postId);
        byte[] result = rabbitMQMessageProducer.publishAndReceive(
                createMediaRequest,
                "internal.exchange",
                "internal.mypost.routing.key"
        );
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new Gson();
        map = (Map<String, Object>) gson.fromJson(new String(result), map.getClass());
        ResponseEntity<String> responseResponseEntity = new ResponseEntity(map, HttpStatus.OK);
        return responseResponseEntity;

    }

}
