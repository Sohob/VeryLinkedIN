package com.verylinkedin.notifications;
public class Notification{
    public String message;
    public String to;


    public Notification() {
        // Must have a public no-argument constructor
    }
    // To single user constructor
    public Notification(String message,String to){
        this.message=message;
        this.to=to;
    }

//To list of users constructor

}
