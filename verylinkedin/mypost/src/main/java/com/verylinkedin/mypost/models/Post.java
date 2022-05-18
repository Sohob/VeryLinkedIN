package com.verylinkedin.mypost.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Field
    private boolean isPublic;
    private ArrayList<Media> media;


}


