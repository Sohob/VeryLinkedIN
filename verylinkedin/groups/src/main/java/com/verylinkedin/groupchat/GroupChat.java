package com.verylinkedin.groupchat;

import lombok.Builder;
import lombok.Data;
import org.bson.json.JsonObject;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;

@Data
@Builder
public class GroupChat {
    private ArrayList<Integer> participants;
    private Integer admin;
    private String title;
    private String description;
    private ArrayList<Message> chatText;
    private String groupPhoto;}
