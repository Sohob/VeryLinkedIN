package com.verylinkedin.mypost.deletePost;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

public record DeletePost(DeletePostRequest request, PostRepository postRepository) {
    public void execute() {
        postRepository.deleteById(request.postId());
    }
}
