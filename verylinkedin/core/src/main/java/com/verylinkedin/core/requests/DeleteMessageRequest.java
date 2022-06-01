package com.verylinkedin.core.requests;

public record DeleteMessageRequest(
        String userId,
        String groupId,
        String messageId
) {
}
