package com.verylinkedin.mypost.ChangeVisibility;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

public record ChangeVisibility(ChangeVisibilityRequest request, PostRepository postRepository) {
    public void execute() {

        Post post = (Post) postRepository.findById(request.postId());
        post.setPublic(!(post.isPublic()));
        postRepository.save(post);
    }
}