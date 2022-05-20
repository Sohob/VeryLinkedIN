package com.verylinkedin.notifications;
public class Notification{
    public String message;
    public String type;
    public String[] users;
    public String to;


    public Notification() {
        // Must have a public no-argument constructor
    }
    // To single user constructor
    public Notification(String message,String to){
        this.message=message;
        this.to=to;
        this.type="user";
    }

//To list of users constructor

    public Notification(String message,String[] users){
        this.message=message;
        this.users=users;
        this.type="list";
    }
}
