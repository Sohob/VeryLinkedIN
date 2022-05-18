package com.verylinkedin.mypost;

import com.verylinkedin.mypost.CreatePost.CreatePost;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest;
import org.springframework.stereotype.Service;

@Service
public record PostService(PostRepository postRepository) {
    public void createPost(CreatePostRequest request){
        CreatePost createGroup = new CreatePost(request, postRepository);
        createGroup.execute();


    }
}
