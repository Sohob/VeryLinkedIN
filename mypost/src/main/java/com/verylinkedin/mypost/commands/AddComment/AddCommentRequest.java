package com.verylinkedin.mypost.commands.AddComment;

public record AddCommentRequest(String postId, String curUserId, String content) {
}
