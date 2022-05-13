package com.verylinkedin.groupchat;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
public class Message {
    private int sender;
    private LocalDateTime time;
    private String content;
    private ArrayList<Integer> read;
    private ArrayList<Integer> unread;
}
