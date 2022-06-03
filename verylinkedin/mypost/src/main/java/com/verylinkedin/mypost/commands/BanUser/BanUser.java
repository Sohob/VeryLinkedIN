package com.verylinkedin.mypost.commands.BanUser;

import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

import java.util.ArrayList;

public record BanUser(BanUserRequest request, PostRepository postRepository)implements Command {
    public Object execute() {
        Post post = (Post) postRepository.findById(request.postId());
        if(post.getBanned() == null)
            post.setBanned(new ArrayList<String>());

        if(!post.getBanned().contains(request.userId())) {
            post.getBanned().add(request.userId());
        }
        postRepository.save(post);
        return "{\"success\":\"true\"}" ;

    }
}
