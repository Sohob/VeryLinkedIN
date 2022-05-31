package com.verylinkedin.mypost.commands.GetPost;

import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public record GetPost(GetPostRequest request, PostRepository postRepository) implements Command {
    public Object execute() {
        List<Post> post =  postRepository.findById(request.postId());
        String json = new Gson().toJson(post );

        // Message build = MessageBuilder.withBody((new String(json)).getBytes()).build();
        return json ;    }
}
