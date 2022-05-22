package com.verylinkedin.groupchat;

import com.verylinkedin.groupchat.rabbitmq.threadPoolManager;

public class CommandRunnable implements Runnable{
    private final Command command;
    public CommandRunnable(Command c){
        this.command = c;
    }

    public void run(){
        command.execute();
        threadPoolManager.onGroupEvent();
    }
}
