package com.verylinkedin.core.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JWToken implements Serializable {
    private String token;
}
