package com.verylinkedin.mypost.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Data
@Document
@Builder


public class Post {
    private String userId;
    @Id
    private String id;
    private String content;
    //    @Version
//    private Long version;
//    @CreatedDate
//    private DateTime createdAt;
//    @LastModifiedDate
//    private DateTime lastModified;
    private boolean isPublic=true;
    private ArrayList<Comment> comments;
    private ArrayList<Media> media;
    private ArrayList<String> likes;
}


