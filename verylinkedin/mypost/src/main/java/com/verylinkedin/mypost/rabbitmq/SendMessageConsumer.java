package com.verylinkedin.mypost.rabbitmq;


import com.verylinkedin.mypost.BanUser.BanUserRequest;
import com.verylinkedin.mypost.ChangeVisibility.ChangeVisibilityRequest;
import com.verylinkedin.mypost.AddComment.AddCommentRequest;
import com.verylinkedin.mypost.EditPost.EditPostRequest;
import com.verylinkedin.mypost.LikePost.LikePostRequest;
import com.verylinkedin.mypost.PostService;
import com.verylinkedin.mypost.UnlikePost.UnlikePostRequest;
import com.verylinkedin.mypost.deletePost.DeletePostRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest ;


@Service
@AllArgsConstructor
@Slf4j
public class SendMessageConsumer {
    private final PostService postService;



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

                CreatePostRequest createPostRequest = new CreatePostRequest(

                        requestJSON.getString("userId"),
                        requestJSON.getString("content"));

                postService.createPost(createPostRequest);
                System.out.print(x);
                break;
            case "com.verylinkedin.mypost.EditPost.EditPostRequest":
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                EditPostRequest editPost = new EditPostRequest(requestJSON.getString("postId"),requestJSON.getString("content"));
                postService.editPost(editPost);
                break;

            case "com.verylinkedin.mypost.deletePost.DeletePostRequest":
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                DeletePostRequest deletePostRequest = new DeletePostRequest(requestJSON.getString("postId"));
                postService.deletePost(deletePostRequest); break;

            case "com.verylinkedin.mypost.ChangeVisibility.ChangeVisibilityRequest":
                System.out.println("leeeeeeh");
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                ChangeVisibilityRequest changeVisibilityRequest= new ChangeVisibilityRequest(requestJSON.getString("userId"),requestJSON.getString("postId"));
                postService.changeVisibility(changeVisibilityRequest);
                break;
            case "com.verylinkedin.mypost.LikePost.LikePostRequest":
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                LikePostRequest likePostRequest = new LikePostRequest(requestJSON.getString("userId"),requestJSON.getString("postId"));
                postService.likePost(likePostRequest);
                break;
            case "com.verylinkedin.mypost.UnlikePost.UnlikePostRequest":
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                UnlikePostRequest unlikePostRequest = new UnlikePostRequest(requestJSON.getString("userId"),requestJSON.getString("postId"));
                postService.unlikePost(unlikePostRequest);
                break;
            case "com.verylinkedin.mypost.AddComment.AddCommentRequest":
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                AddCommentRequest addCommentRequest = new AddCommentRequest(requestJSON.getString("postId"), requestJSON.getString("userId"), requestJSON.getString("content"));
                postService.addComment(addCommentRequest);break;

            case "com.verylinkedin.mypost.BanUser.BanUserRequest":
                requestJSON = new JSONObject(new String(requestFromQueue.getBody()));
                BanUserRequest banUserRequest = new BanUserRequest(requestJSON.getString("postId"), requestJSON.getString("userId"));
                postService.banUser(banUserRequest);break;
        }




    }
}
