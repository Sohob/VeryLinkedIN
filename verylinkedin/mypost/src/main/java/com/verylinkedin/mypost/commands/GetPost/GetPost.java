package com.verylinkedin.mypost.commands.GetPost;

import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import org.springframework.data.mongodb.core.mapping.Document;

public record GetPost(GetPostRequest request, PostRepository postRepository) implements Command {
    public Object execute() {
        Document post = (Document) postRepository.findById(request.postId());
        return   post;
    }
}
