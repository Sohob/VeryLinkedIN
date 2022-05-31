package com.verylinkedin.mypost;

import com.verylinkedin.mypost.commands.BanUser.BanUser;
import com.verylinkedin.mypost.commands.BanUser.BanUserRequest;
import com.verylinkedin.mypost.commands.ChangeVisibility.ChangeVisibility;
import com.verylinkedin.mypost.commands.ChangeVisibility.ChangeVisibilityRequest;
import com.verylinkedin.mypost.commands.AddComment.AddComment;
import com.verylinkedin.mypost.commands.AddComment.AddCommentRequest;
import com.verylinkedin.mypost.commands.CreatePost.CreatePost;
import com.verylinkedin.mypost.commands.CreatePost.CreatePostRequest;
import com.verylinkedin.mypost.commands.EditPost.EditPost;
import com.verylinkedin.mypost.commands.EditPost.EditPostRequest;
import com.verylinkedin.mypost.commands.LikePost.LikePost;
import com.verylinkedin.mypost.commands.LikePost.LikePostRequest;
import com.verylinkedin.mypost.commands.UnlikePost.UnlikePost;
import com.verylinkedin.mypost.commands.UnlikePost.UnlikePostRequest;
import com.verylinkedin.mypost.commands.DeletePost.DeletePost;
import com.verylinkedin.mypost.commands.DeletePost.DeletePostRequest;

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

    public void likePost(LikePostRequest request){
        LikePost likePost = new LikePost(request, postRepository);
        likePost.execute();
    }
    public void unlikePost(UnlikePostRequest request){
        UnlikePost unlikePost = new UnlikePost(request, postRepository);
        unlikePost.execute();
    }

}
