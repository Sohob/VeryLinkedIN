package com.verylinkedin.reporting;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class CommandMap {
    public static HashMap<String, Class> commandMap = new HashMap<>();
    public static HashMap<String, Class> requestMap = new HashMap<>();
    private static CommandMap INSTANCE;

    private CommandMap() {

        List<Class> requestClassList = findAllClassesUsingClassLoader("com.verylinkedin.reporting.requests");
        List<Class> commandClassList = findAllClassesUsingClassLoader("com.verylinkedin.reporting.commands");
        Collections.sort(requestClassList, Comparator.comparing(Class::getName));
        Collections.sort(commandClassList, Comparator.comparing(Class::getName));
        for (int i = 0; i < requestClassList.size(); i++) {
            Class requestClass = requestClassList.get(i);
            Class commandClass = commandClassList.get(i);
            String requestClassName = requestClass.getName();
            String commandClassName = commandClass.getName();
            commandMap.put("com.verylinkedin.core." + requestClassName.substring(27), commandClass);
            requestMap.put("com.verylinkedin.core." + requestClassName.substring(27), requestClass);
        }
    }

    public static CommandMap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommandMap();
        }

        return INSTANCE;
    }

    public static HashMap<String, Class> getCommandMap() {
        return commandMap;
    }

    public static Class getCommandClass(String type) {
        return commandMap.get(type);
    }

    public static Class getRequestClass(String type) {
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

    @Override
    public String toString() {
        return commandMap + " " + requestMap;
    }
}
