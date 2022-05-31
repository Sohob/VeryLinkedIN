package com.verylinkedin.mypost.GetPost;


import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.Response;
import com.verylinkedin.mypost.models.Post;
import net.minidev.json.JSONArray;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public record GetPosts(GetPostsRequest request, PostRepository postRepository) implements Command {
    public String execute() {
        List<Post> result = postRepository.findByUserId(request.userId());
        //byte[] body = message.getBody();
        String json = new Gson().toJson(result );

       // Message build = MessageBuilder.withBody((new String(json)).getBytes()).build();
        return json ;

    }

}
