package at.htlkaindorf.mh.database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;

import java.io.*;

/**
 * <b>Movieholic</b><br><br>
 * Creates a Firebase (Firestore) Database connection.<br>
 * @author David
 * @version 1.0
 * @since last update: 23.05.2022
 */
public class FirebaseService {
    private static Firestore db;

    /**
     * Returns Firestore Database Connection, if it doesn't exist yet, it will be created.
     * @return {@link Firestore}
     * @throws IOException
     */
    public static Firestore getFireBaseDatabase() throws IOException {
        if (db == null) {
            new FirebaseService();
        }
        return db;
    }

    /**
     * Creates a Firestore Database connection.
     * @throws IOException
     */
    private FirebaseService() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("serviceWorkerKey.json");

        assert is != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(is))
                .setDatabaseUrl("http://movieholic-72a5e-default-rtdb.europe-west1.firebasedatabase.app/")
                .build();

        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }
}
