package com.verylinkedin.mypost.commands.CreatePost;

import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

public record CreatePost(CreatePostRequest request,PostRepository postRepository) implements Command {

    public Object execute() {
        Post post = Post.builder()
                .userId(request.userId())
                .content(request.content())
                .build();
        post.setPublic(true);
        postRepository.save(post);
        String json = new Gson().toJson(post);
        return json ;

    }
}
