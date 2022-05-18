package com.verylinkedin.mypost.models;

import org.springframework.data.annotation.Id;

public class Comment {
    @Id
    private String id;
    @Id
    private String userId;
}
