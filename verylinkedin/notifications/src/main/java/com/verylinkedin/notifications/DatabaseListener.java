package com.verylinkedin.notifications;


import com.google.api.core.SettableApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.DocumentChange.Type;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import java.util.Collection;

public class DatabaseListener {
    public DatabaseListener(String user) throws IOException {
        FirebaseInitialize.initialize();
        System.out.print("User: " +user);
        Firestore db= FirestoreClient.getFirestore();
    db.collection(user).addSnapshotListener(
            (snapshots, e) -> {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            System.out.println("New Notification: " + dc.getDocument().getData());
                            break;
                        default:
                            break;
                    }
                }
            });
    }

    public static void main(String[] args) throws IOException {
        DatabaseListener listener=new DatabaseListener("user1");
    }
}
