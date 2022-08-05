package com.verylinkedin.core.requests;

public class AddNotificationList {
    public String message;
    public String[] to;

    public AddNotificationList() {
        // Must have a public no-argument constructor
    }

    public AddNotificationList(String message, String[] to) {
        this.message = message;
        this.to = to;
    }
}
