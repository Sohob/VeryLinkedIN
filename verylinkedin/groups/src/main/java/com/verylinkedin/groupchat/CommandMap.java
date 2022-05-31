package com.verylinkedin.groupchat;

import com.google.common.reflect.ClassPath;
import com.verylinkedin.groupchat.commands.CreateGroupCommand;
import com.verylinkedin.groupchat.commands.SendMessageCommand;
import com.verylinkedin.groupchat.commands.ViewChatCommand;
import com.verylinkedin.groupchat.requests.CreateGroupRequest;
import com.verylinkedin.groupchat.requests.SendMessageRequest;
import com.verylinkedin.groupchat.requests.ViewChatRequest;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CommandMap {
    public static HashMap<String, Class> commandMap = new HashMap<>();
    public static HashMap<String, Class> requestMap = new HashMap<>();

    public CommandMap() throws IOException {
        /*
        List<Class> requestClassList = findAllClassesUsingGoogleGuice("com.verylinkedin.groupchat.requests");
        List<Class> commandClassList = findAllClassesUsingGoogleGuice("com.verylinkedin.groupchat.commands");
        Collections.sort(requestClassList, Comparator.comparing(Class::getName));
        Collections.sort(commandClassList, Comparator.comparing(Class::getName));
        for(int i = 0;i < requestClassList.size();i++){
            Class requestClass = requestClassList.get(i);
            Class commandClass = commandClassList.get(i);
            String requestClassName = requestClass.getName();
            String commandClassName = commandClass.getName();
            commandMap.put("com.verylinkedin.core.requests."+requestClassName.substring(36), commandClass);
            requestMap.put("com.verylinkedin.core.requests."+requestClassName.substring(36), requestClass);
        }
        System.out.println(commandMap);
        System.out.println(commandClassList);*/
        commandMap.put("com.verylinkedin.core.requests.SendMessageRequest", SendMessageCommand.class);
        requestMap.put("com.verylinkedin.core.requests.SendMessageRequest", SendMessageRequest.class);
        commandMap.put("com.verylinkedin.core.requests.CreateGroupRequest", CreateGroupCommand.class);
        requestMap.put("com.verylinkedin.core.requests.CreateGroupRequest", CreateGroupRequest.class);
        commandMap.put("com.verylinkedin.core.requests.ViewChatRequest", ViewChatCommand.class);
        requestMap.put("com.verylinkedin.core.requests.ViewChatRequest", ViewChatRequest.class);
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
    public List<Class> findAllClassesUsingGoogleGuice(String packageName) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(packageName))
                .map(clazz -> clazz.load())
                .collect(Collectors.toList());
    }
}
