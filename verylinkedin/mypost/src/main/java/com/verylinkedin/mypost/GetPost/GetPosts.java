package com.verylinkedin.mypost.GetPost;


import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.Response;
import com.verylinkedin.mypost.models.Post;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public record GetPosts(GetPostsRequest request, PostRepository postRepository) implements Command {
    public Object execute() {
        List<Post> result = postRepository.findByUserId(request.userId());
        return new Response( result.toString()).toString();

    }

}
