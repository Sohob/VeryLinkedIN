package com.verylinkedin.groupchat.classes;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

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
