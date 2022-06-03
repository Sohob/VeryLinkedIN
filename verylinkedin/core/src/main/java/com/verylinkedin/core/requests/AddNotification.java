package com.verylinkedin.core.requests;

public class AddNotification {
    public String message;
    public String to;


    public AddNotification() {
        // Must have a public no-argument constructor
    }

    public AddNotification(String message, String to) {
        this.message = message;
        this.to = to;
    }

}
