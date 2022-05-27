package com.verylinkedin.core.requests;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record SendMessageRequest( String userId,
                                  String groupId,
                                  String message) {
}
