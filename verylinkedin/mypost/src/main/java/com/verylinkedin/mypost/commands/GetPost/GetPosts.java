package com.verylinkedin.mypost.commands.GetPost;


import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

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
