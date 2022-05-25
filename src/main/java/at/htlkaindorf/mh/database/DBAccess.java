package at.htlkaindorf.mh.database;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DBAccess {

    private static DBAccess instance;

    private Firestore db;
    private CollectionReference ref;

    private DBAccess() throws IOException {
        db = FirebaseService.getFireBaseDatabase();
        ref = db.collection("users");
    }

    public synchronized static DBAccess getInstance() throws Exception {
        if (instance == null){
            instance = new DBAccess();
        }
        return instance;
    }

    public void addFavoriteToUser(String uid, int movieId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = ref.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("favorites", FieldValue.arrayUnion(movieId));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }

    public void removeFavoriteFromUser(String uid, int movieId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = ref.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("favorites", FieldValue.arrayRemove(movieId));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");

        DBAccess.getInstance().addFavoriteToUser("1Pum18WS6YYWZtZz1KZYrlZAVgl1", 453394);
        DBAccess.getInstance().removeFavoriteFromUser("1Pum18WS6YYWZtZz1KZYrlZAVgl1", 453393);
    }
}
