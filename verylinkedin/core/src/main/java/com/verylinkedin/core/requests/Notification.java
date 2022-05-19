package com.verylinkedin.core.requests;

public class Notification {

    public String message;
    public String to;
    public String type;

    public Notification() {
        // Must have a public no-argument constructor
    }

    public Notification(String m,String t,String type){
        this.to=t;
        this.message=m;
        this.type=type;
    }


}
