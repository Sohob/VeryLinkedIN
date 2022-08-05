package com.verylinkedin.editprofile;

import com.google.common.reflect.ClassPath;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CommandMap {
    public static HashMap<String, Class> commandMap = new HashMap<>();
    public static HashMap<String, Class> requestMap = new HashMap<>();

    public CommandMap() throws IOException {
        //commandMap.put("com.verylinkedin.core.requests.SendMessageRequest", SendMessageCommand.class);
       // requestMap.put("com.verylinkedin.core.requests.SendMessageRequest", SendMessageRequest.class);

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
