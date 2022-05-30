package com.verylinkedin.mypost.ChangeVisibility;

import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

public record ChangeVisibility(ChangeVisibilityRequest request, PostRepository postRepository) implements Command {
    public Object execute() {


        Post post = (Post) postRepository.findById(request.postId());
        System.out.println(post.getUserId()+" "+request.userId());
        if(post.getUserId().equals(request.userId())) {
            post.setPublic(!(post.isPublic()));
        }
        postRepository.save(post);
        return null ;

    }
}
