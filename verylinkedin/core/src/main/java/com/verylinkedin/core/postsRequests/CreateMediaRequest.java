package com.verylinkedin.core.postsRequests;

public record CreateMediaRequest(byte[] file, String imageName, String contentType, String postId, String curUserId) {
}
