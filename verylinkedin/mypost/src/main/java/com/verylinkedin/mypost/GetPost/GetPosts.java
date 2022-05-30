package com.verylinkedin.mypost.GetPost;


import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;
import org.springframework.data.mongodb.core.mapping.Document;

public record GetPosts(GetPostsRequest request, PostRepository postRepository) {
    public Document execute() {
        Document post = (Document) postRepository.findByUserId(request.userId());
        return   post;
    }
}
