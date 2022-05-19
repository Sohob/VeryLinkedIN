package com.verylinkedin.mypost.deletePost;

import com.verylinkedin.mypost.PostRepository;

public record DeletePost(DeletePostRequest request, PostRepository postRepository) {
    public void execute() {
        postRepository.deleteById(request.postId());
    }
}
