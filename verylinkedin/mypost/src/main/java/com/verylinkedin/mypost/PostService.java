package com.verylinkedin.mypost;

import com.verylinkedin.mypost.BanUser.BanUser;
import com.verylinkedin.mypost.BanUser.BanUserRequest;
import com.verylinkedin.mypost.ChangeVisibility.ChangeVisibility;
import com.verylinkedin.mypost.ChangeVisibility.ChangeVisibilityRequest;
import com.verylinkedin.mypost.AddComment.AddComment;
import com.verylinkedin.mypost.AddComment.AddCommentRequest;
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
    public void changeVisibility(ChangeVisibilityRequest request) {
        ChangeVisibility changeVisibility = new ChangeVisibility(request, postRepository);
        changeVisibility.execute();
    }

    public void addComment(AddCommentRequest request) {
        AddComment addComment = new AddComment(request, postRepository);
        addComment.execute();
    }

    public void banUser(BanUserRequest request) {
        BanUser banUser = new BanUser(request, postRepository);
        banUser.execute();
    }
}
