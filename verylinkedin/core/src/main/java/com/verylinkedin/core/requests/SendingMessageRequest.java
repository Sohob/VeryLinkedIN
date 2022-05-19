package com.verylinkedin.core.requests;

public record SendingMessageRequest(String userId,
                                    String groupId,
                                    String message) {
}
