package com.verylinkedin.core.requests;

public record DeleteGroupRequest(
        String userId,
        String groupId
) {
}
