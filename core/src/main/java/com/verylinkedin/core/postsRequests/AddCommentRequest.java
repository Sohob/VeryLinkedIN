package com.verylinkedin.core.postsRequests;

public record AddCommentRequest(String postId, String curUserId, String content) {
}
