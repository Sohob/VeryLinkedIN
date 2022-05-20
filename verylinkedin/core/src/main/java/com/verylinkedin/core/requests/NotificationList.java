package com.verylinkedin.core.requests;

public class NotificationList{
    public String message;
    public String[] users;



    public NotificationList() {
        // Must have a public no-argument constructor
    }

//To list of users constructor

    public NotificationList(String message,String[] users){
        this.message=message;
        this.users=users;
    }
}
