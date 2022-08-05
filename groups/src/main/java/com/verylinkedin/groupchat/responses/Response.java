package com.verylinkedin.groupchat.responses;


import org.springframework.http.HttpStatus;

import java.io.Serializable;

public record Response(String body, HttpStatus status) implements Serializable {
}
