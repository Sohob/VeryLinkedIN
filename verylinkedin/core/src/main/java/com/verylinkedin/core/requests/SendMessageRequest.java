package com.verylinkedin.core.requests;

public record SendMessageRequest(
        String userId,
        String groupId,
        String message) {
}
