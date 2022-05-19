package com.verylinkedin.notifications;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.Map;

public class AddNotification implements Command{
    public Notification notification;

    public AddNotification(Notification n){
        this.notification=n;

    }
    public void execute(){
        Firestore db= FirestoreClient.getFirestore();
        Map<String, Object> docData = new HashMap<>();
        docData.put("message",notification.message);
        ApiFuture<WriteResult> future = db.collection(notification.to).document().set(docData);
    }
}
