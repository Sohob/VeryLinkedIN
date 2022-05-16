package com.verylinkedin.groupchat;

import lombok.Builder;
import lombok.Data;
import org.bson.json.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;

@Data
@Builder
public class GroupChat {
    @Id
    private String id;
    private ArrayList<String> participants;
    private String admin;
    private String title;
    private String description;
    private ArrayList<Message> chatText;
    private String groupPhoto;}
