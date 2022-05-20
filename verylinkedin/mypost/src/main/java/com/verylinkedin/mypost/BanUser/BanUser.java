package com.verylinkedin.mypost.BanUser;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

import java.util.ArrayList;

public record BanUser(BanUserRequest request, PostRepository postRepository) {
    public void execute() {
        Post post = (Post) postRepository.findById(request.postId());
        if(post.getBanned() == null)
            post.setBanned(new ArrayList<String>());

        if(!post.getBanned().contains(request.userId())) {
            post.getBanned().add(request.userId());
        }
        postRepository.save(post);
    }
}