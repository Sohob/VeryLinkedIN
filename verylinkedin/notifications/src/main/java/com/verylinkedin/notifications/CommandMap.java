package com.verylinkedin.notifications;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandMap {
    public static HashMap<String, Class> commandMap = new HashMap<>();
    public static HashMap<String, Class> requestMap = new HashMap<>();
    private static CommandMap INSTANCE;

    private CommandMap() {
        List<Class> commandClassList = findAllClassesUsingClassLoader("com.verylinkedin.notifications.commands");
        Collections.sort(commandClassList, Comparator.comparing(Class::getName));
        for(int i = 0;i < commandClassList.size();i++){
            Class commandClass = commandClassList.get(i);
            String commandClassName = commandClass.getName();
            commandMap.put(commandClassName.substring(40,commandClassName.length()-7), commandClass);
        }
        System.out.println(commandMap);
    }

    public static CommandMap getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CommandMap();
        }

        return INSTANCE;
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

    public List<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toList());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
