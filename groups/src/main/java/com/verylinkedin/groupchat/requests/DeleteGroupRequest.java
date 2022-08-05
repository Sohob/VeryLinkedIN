package com.verylinkedin.groupchat.requests;

public record DeleteGroupRequest(
        String userId,
        String groupId
) {
}
