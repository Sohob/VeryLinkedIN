package com.verylinkedin.core.postsRequests;

public record AddCommentRequest(String postId, String userId, String content) {
}
