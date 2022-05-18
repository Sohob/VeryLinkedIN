package com.verylinkedin.mypost;

import com.verylinkedin.mypost.CreatePost.CreatePost;
import com.verylinkedin.mypost.CreatePost.CreatePostRequest;
import com.verylinkedin.mypost.EditPost.EditPost;
import com.verylinkedin.mypost.EditPost.EditPostRequest;
import com.verylinkedin.mypost.deletePost.DeletePost;
import com.verylinkedin.mypost.deletePost.DeletePostRequest;
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

    public void deletePost(DeletePostRequest request) {
        DeletePost deletePost = new DeletePost(request, postRepository);
        deletePost.execute();
    }
}
