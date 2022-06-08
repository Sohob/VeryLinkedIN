package com.verylinkedin.mypost.commands.EditPost;

public record EditPostRequest(String postId, String content,String curUserId)  {
}
