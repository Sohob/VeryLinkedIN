package com.verylinkedin.mypost.AddComment;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Comment;
import com.verylinkedin.mypost.models.Post;

import java.util.ArrayList;

public record AddComment(AddCommentRequest request, PostRepository postRepository) {
    public void execute() {
        Post post = (Post) postRepository.findById(request.postId());
        Comment comment = Comment.builder()
                .userId(request.userId())
                .content(request.content())
                .build();
        if(post.getComments() == null)
            post.setComments(new ArrayList<Comment>());

        if(post.getBanned() == null || !post.getBanned().contains(request.userId())) {
            post.getComments().add(comment);
            postRepository.save(post);
        }
    }
}
