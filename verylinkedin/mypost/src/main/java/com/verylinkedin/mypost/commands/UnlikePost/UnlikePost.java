package com.verylinkedin.mypost.commands.UnlikePost;

import com.google.gson.Gson;
import com.verylinkedin.mypost.Command;
import com.verylinkedin.mypost.PostRepository;
import com.verylinkedin.mypost.models.Post;

import java.util.ArrayList;

public record UnlikePost(UnlikePostRequest request, PostRepository postRepository) implements Command {
    public Object execute() {
        Post post = postRepository.findById(request.postId());
        ArrayList<String> likes = post.getLikes();

        if (likes != null && likes.contains(request.curUserId())) {
            likes.remove(request.curUserId());
            post.setLikes(likes);
            postRepository.save(post);
        }
        String json = new Gson().toJson(post);
        return json;

    }
}
