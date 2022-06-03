package com.verylinkedin.notifications.commands;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.verylinkedin.notifications.Command;
import com.verylinkedin.notifications.Notification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddNotificationCommand implements Command {
    public JSONObject body;

    public AddNotificationCommand(JSONObject body){
        this.body =body;

    }
    public Object execute(){
        try {
            Firestore db= FirestoreClient.getFirestore();
            Map<String, Object> docData = new HashMap<>();
            docData.put("message",body.getString("message"));
            db.collection(body.getString("to")).document().set(docData);
            return "Notification Sent";
        }catch (Exception e){
            System.out.println(e);
            return "Error sending notification";
        }




    }
}
