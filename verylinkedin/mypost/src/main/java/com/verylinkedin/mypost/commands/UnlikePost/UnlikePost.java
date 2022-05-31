package com.verylinkedin.mypost.commands.UnlikePost;

import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

import java.util.ArrayList;

public record UnlikePost(UnlikePostRequest request, PostRepository postRepository) implements Command {
    public Object execute() {
        Post post = (Post) postRepository.findById(request.postId());
        ArrayList<String> likes = post.getLikes();

        if(likes != null && likes.contains(request.userId())){
            likes.remove(request.userId());
            post.setLikes(likes);
            postRepository.save(post);
        }
        return null ;

    }
}
