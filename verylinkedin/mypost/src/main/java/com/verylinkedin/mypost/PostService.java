package com.verylinkedin.mypost;

import com.verylinkedin.mypost.CreatePost.CreatePost;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest;
import com.verylinkedin.mypost.EditPost.EditPost;
import com.verylinkedin.mypost.EditPost.EditPostRequest;
import org.springframework.stereotype.Service;

@Service
public record PostService(PostRepository postRepository) {
    public void createPost(CreatePostRequest request){
        CreatePost createGroup = new CreatePost(request, postRepository);
        createGroup.execute();
    }

    public void editPost(EditPostRequest request){
        EditPost editPost = new EditPost(request, postRepository);
        editPost.execute();
    }
}
