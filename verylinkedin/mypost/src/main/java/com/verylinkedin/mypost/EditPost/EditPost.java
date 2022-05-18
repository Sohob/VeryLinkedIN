package com.verylinkedin.mypost.EditPost;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

public record EditPost(EditPostRequest request, PostRepository postRepository) {
    public void execute() {
        Post post = (Post) postRepository.findById(request.postId());
        post.setContent(request.content());

        postRepository.save(post);
    }
}
