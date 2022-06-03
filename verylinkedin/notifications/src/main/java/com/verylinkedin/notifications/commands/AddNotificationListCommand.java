package com.verylinkedin.notifications.commands;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.verylinkedin.notifications.Command;
import com.verylinkedin.notifications.NotificationList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddNotificationListCommand implements Command {
    public JSONObject body;

    public AddNotificationListCommand(JSONObject body){
        this.body=body;

    }
    public Object execute(){
        try {
            Firestore db = FirestoreClient.getFirestore();

            JSONArray arr=body.getJSONArray("to");

                for(int i=0;i<arr.length();i++){

                 String id=arr.getString(i);
                    System.out.println(id);
                Map<String, Object> docData = new HashMap<>();
                docData.put("message", body.getString("message"));
                db.collection(id).document().set(docData);
            }
            return "Notification Sent";
        }catch (Exception e){
            System.out.println(e);
            return "Error sending notification";
        }
    }
}
