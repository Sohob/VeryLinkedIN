package com.verylinkedin.groupchat.sendmessage;

public record SendingMessageRequest(String userId,
                                    String groupId,
                                    String message) {
}
