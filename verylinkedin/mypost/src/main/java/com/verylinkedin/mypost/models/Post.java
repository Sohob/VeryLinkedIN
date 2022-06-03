package com.verylinkedin.mypost.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.jetbrains.annotations.Contract;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Data
@Document
@Builder
@ToString


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
    private boolean isPublic;
    private ArrayList<Comment> comments;
    private ArrayList<Media> media;
    private ArrayList<String> likes;
    private ArrayList<String> banned;

    @Override
    public String toString() {
        return "Post{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", isPublic=" + isPublic +
                ", comments=" + comments +
                ", media=" + media +
                ", likes=" + likes +
                ", banned=" + banned +
                '}';
    }
}

