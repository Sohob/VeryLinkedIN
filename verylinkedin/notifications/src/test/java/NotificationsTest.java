import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.verylinkedin.core.requests.Notification;
import com.verylinkedin.core.requests.NotificationList;
import com.verylinkedin.notifications.FirebaseInitialize;
import com.verylinkedin.notifications.NotificationController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class NotificationsTest {
    public final MessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();

    @Test
    public void testNotification() throws IOException {
        try{
            FirebaseInitialize.initialize();
            Firestore db=FirestoreClient.getFirestore();
            deleteCollection(db.collection("testUser"),10);
            String m="single user test";
            Notification notification=new Notification(m,"testUser");
            Message message=jackson2JsonMessageConverter.toMessage(notification,null);
            NotificationController.Notification(message);
            Boolean flag=checkUserHasMessage(db,"testUser",m);
            Assert.assertEquals(flag,true);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Test
    public void testNotificationList() throws IOException {
        try{
            FirebaseInitialize.initialize();
            Firestore db=FirestoreClient.getFirestore();
            String[] users={"testUser1","testUser2","testUser3"};
            for(String user:users){
                deleteCollection(db.collection(user),10);
            }
            String m="testing list";
            NotificationList notification=new NotificationList(m,users);
            Message message=jackson2JsonMessageConverter.toMessage(notification,null);
            NotificationController.Notification(message);
            boolean flag=true;
            for(String user:users){
                flag=checkUserHasMessage(db,user,m);
            }
            Assert.assertEquals(flag,true);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static Boolean checkUserHasMessage(Firestore db,String user,String message) throws ExecutionException, InterruptedException {
        try {
            Iterable<DocumentReference> list = db.collection(user).listDocuments();
            Boolean flag = false;
            for (DocumentReference ref : list) {
                Map<String, Object> doc = db.collection(user).document(ref.getId()).get().get().getData();
                String m = (String) doc.get("message");
                if (m.equals(message)) {
                    flag = true;
                }
            }
            return flag;
        }catch (Exception e){
            return false;
        }
    }


    void deleteCollection(CollectionReference collection, int batchSize) {
        try {
            // retrieve a small batch of documents to avoid out-of-memory errors
            ApiFuture<QuerySnapshot> future = collection.limit(batchSize).get();
            int deleted = 0;
            // future.get() blocks on document retrieval
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                document.getReference().delete();
                ++deleted;
            }
            if (deleted >= batchSize) {
                // retrieve and delete another batch
                deleteCollection(collection, batchSize);
            }
        } catch (Exception e) {
            System.err.println("Error deleting collection : " + e.getMessage());
        }
    }
}
