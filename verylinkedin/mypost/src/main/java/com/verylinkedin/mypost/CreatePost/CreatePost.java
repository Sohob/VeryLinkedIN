package com.verylinkedin.mypost.CreatePost;

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
        return null ;

    }
}
