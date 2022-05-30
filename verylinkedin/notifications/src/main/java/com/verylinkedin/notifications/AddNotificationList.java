package com.verylinkedin.notifications;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNotificationList implements Command{
    public NotificationList notificationList;

    public AddNotificationList(NotificationList notificationList){
        this.notificationList=notificationList;

    }
    public void execute(){
        Firestore db= FirestoreClient.getFirestore();
        for(String id: notificationList.users){
            Map<String, Object> docData = new HashMap<>();
            docData.put("message",notificationList.message);
            db.collection(id).document().set(docData);
        }

    }
}
