package com.veryLinkedin.reporting;

public class CommandRunnable implements Runnable {
    private final Command command;

    public CommandRunnable(Command c) {
        this.command = c;
    }

    public void run() {
        command.execute();
    }
}
