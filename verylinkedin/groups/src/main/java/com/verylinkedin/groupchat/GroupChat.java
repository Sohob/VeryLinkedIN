package com.verylinkedin.groupchat;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class GroupChat {
    private ArrayList<Integer> participants;
    private ArrayList<Integer> admins;
    private String title;
    private String description;
    private String chatText;
    private String groupPhoto;
}
