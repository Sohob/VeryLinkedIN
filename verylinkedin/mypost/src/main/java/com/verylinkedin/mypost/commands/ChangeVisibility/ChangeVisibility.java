package com.verylinkedin.mypost.commands.ChangeVisibility;

import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

public record ChangeVisibility(ChangeVisibilityRequest request, PostRepository postRepository) implements Command {
    public Object execute() {


        Post post = postRepository.findById(request.postId());
        if (post.getUserId().equals(request.curUserId())) {
            post.setPublic(!(post.isPublic()));
        }
        postRepository.save(post);
        return "{\"success\":\"true\"}";

    }
}
