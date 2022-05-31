package com.verylinkedin.mypost.commands.AddComment;

public record AddCommentRequest(String postId, String userId, String content) {
}
