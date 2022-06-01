package com.verylinkedin.groupchat.requests;

import com.verylinkedin.groupchat.classes.Message;

public record EditMessageRequest(
        String userId,
        String groupId,
        String messageId,
        String editedMessage
) {
}
