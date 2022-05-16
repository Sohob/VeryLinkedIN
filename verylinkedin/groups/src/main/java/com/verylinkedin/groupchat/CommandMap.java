package com.verylinkedin.groupchat;

import com.verylinkedin.groupchat.sendmessage.SendingMessageRequest;

import java.util.HashMap;

public class CommandMap {
    public static HashMap<String, Class> commandMap = new HashMap<>();

    public CommandMap(){
        commandMap.put("com.verylinkedin.groupchat.sendmessage.SendingMessageRequest", SendingMessageRequest.class);
    }

    public static HashMap<String, Class> getCommandMap() {
        return commandMap;
    }
}
