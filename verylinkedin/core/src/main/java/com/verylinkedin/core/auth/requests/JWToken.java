package com.verylinkedin.core.auth.requests;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JWToken implements Serializable {
    private String token;
}
