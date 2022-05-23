package at.htlkaindorf.mh.database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.*;

public class FirebaseService {
    private static FirebaseDatabase db;

    public static FirebaseDatabase getFireBaseDatabase() throws IOException {
        if (db == null) {
            new FirebaseService();
        }
        return db;
    }

    public FirebaseService() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("serviceWorkerKey.json");

        assert is != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(is))
                .setDatabaseUrl("https://movieholic-72a5e-default-rtdb.europe-west1.firebasedatabase.app/")
                .build();

        FirebaseApp.initializeApp(options);

        db = FirebaseDatabase.getInstance();
    }
}
