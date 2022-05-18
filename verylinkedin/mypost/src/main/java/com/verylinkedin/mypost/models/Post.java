package com.verylinkedin.mypost.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Post {
    private int userId;

    @Id
    private int id;
    private String content;
    //    @Version
//    private Long version;
//    @CreatedDate
//    private DateTime createdAt;
//    @LastModifiedDate
//    private DateTime lastModified;
    private boolean isPublic;
    private ArrayList<Media> media;


}


