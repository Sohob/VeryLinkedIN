package com.verylinkedin.groupchat;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
@ToString
public class Message {
    private String sender;
    private LocalDateTime time;
    private String content;
    private ArrayList<String> read;
    private ArrayList<String> unread;
}
