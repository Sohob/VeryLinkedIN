package com.verylinkedin.groupchat;

public class CommandRunnable implements Runnable{
    private final Command command;
    public CommandRunnable(Command c){
        this.command = c;
    }

    public void run(){
        command.execute();
    }
}
