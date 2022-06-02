package com.verylinkedin.core.auth.commands;

import java.io.IOException;
import java.util.HashMap;
public class CommandMap {
    public static HashMap<String, Class> commandMap = new HashMap<>();
    public static HashMap<String, Class> requestMap = new HashMap<>();

    public CommandMap() throws IOException {
        commandMap.put("com.verylinkedin.core.requests.AddRedisCommand", AddTokenCommand.class);
        requestMap.put("com.verylinkedin.core.requests.AddRedisCommand", AddTokenCommand.class);
        commandMap.put("com.verylinkedin.core.requests.RemoveRedisCommand", RemoveTokenCommand.class);
        requestMap.put("com.verylinkedin.core.requests.RemoveRedisCommand", RemoveTokenCommand.class);
    }

    public static HashMap<String, Class> getCommandMap() {
        return commandMap;
    }

    public static Class getCommandClass(String type){
        return commandMap.get(type);
    }
    public static Class getRequestClass(String type){
        return requestMap.get(type);
    }

}
