package com.verylinkedin.groupchat.requests;

public record DeleteMessageRequest(
        String userId,
        String groupId,
        String messageId
) {
}
