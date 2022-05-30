package com.verylinkedin.mypost.deletePost;

import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;

public record DeletePost(DeletePostRequest request, PostRepository postRepository)implements Command {
    public Object execute() {
        postRepository.deleteById(request.postId());
        return null ;

    }
}
