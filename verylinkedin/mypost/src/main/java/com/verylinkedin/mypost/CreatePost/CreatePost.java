package com.verylinkedin.mypost.CreatePost;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

public record CreatePost(CreatePostRequest request,PostRepository postRepository) {

    public void execute() {
        Post post = Post.builder()
                .userId(request.userId())
                .content(request.content())
                .build();
        post.setPublic(true);
        postRepository.save(post);
    }
}
