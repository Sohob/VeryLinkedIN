package com.verylinkedin.mypost.UnlikePost;

import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

import java.util.ArrayList;

public record UnlikePost(UnlikePostRequest request, PostRepository postRepository) {
    public void execute() {
        Post post = (Post) postRepository.findById(request.postId());
        ArrayList<String> likes = post.getLikes();

        if(likes != null && likes.contains(request.userId())){
            likes.remove(request.userId());
            post.setLikes(likes);
            postRepository.save(post);
        }
    }
}
