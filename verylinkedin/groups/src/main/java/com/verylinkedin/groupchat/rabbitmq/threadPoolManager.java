package com.verylinkedin.groupchat.rabbitmq;

public class threadPoolManager {
    //ToDo Assign thread number based on config file
    private static int threads = 5;

    public static int getThreads(){
        return threads;
    }
    public static void setThreads(int threads) {
        threadPoolManager.threads = threads;
    }

    public static void onGroupEvent() {
        threads += 1;
    }
}
