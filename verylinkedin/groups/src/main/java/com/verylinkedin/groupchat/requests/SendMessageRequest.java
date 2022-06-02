package com.verylinkedin.groupchat.requests;

public record SendMessageRequest(
        String userId,
        String groupId,
        String message) {
}
