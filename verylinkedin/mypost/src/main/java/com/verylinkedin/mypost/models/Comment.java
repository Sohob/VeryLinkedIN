package com.verylinkedin.mypost.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder

public class Comment {
    @Id
    private String id;
    private String userId;
    private String postId;
    private String content;
}
