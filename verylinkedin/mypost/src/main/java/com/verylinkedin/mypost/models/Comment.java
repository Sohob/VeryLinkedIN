package com.verylinkedin.mypost.models;

import org.springframework.data.annotation.Id;

public class Comment {
    private String id;
    private String userId;

    public Comment(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
