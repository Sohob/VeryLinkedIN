package com.verylinkedin.groupchat.requests;

public record EditMessageRequest(
        String userId,
        String groupId,
        String messageId,
        String editedMessage
) {
}
