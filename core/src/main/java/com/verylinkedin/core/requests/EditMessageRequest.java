package com.verylinkedin.core.requests;

public record EditMessageRequest(
        String userId,
        String groupId,
        String messageId,
        String editedMessage
) {
}
