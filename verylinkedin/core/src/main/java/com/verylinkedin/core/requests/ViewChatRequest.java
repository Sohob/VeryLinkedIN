package com.verylinkedin.core.requests;

public record ViewChatRequest(
        String userId,
        String groupId
) {
}
