package com.verylinkedin.groupchat.requests;

public record ViewChatRequest(
        String userId,
        String groupId
) {
}
