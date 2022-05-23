package at.htlkaindorf.mh.database;
import com.google.api.core.ApiFuture;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DBAccess {

    private static DBAccess instance;

    private FirebaseDatabase db;
    private DatabaseReference ref;

    private DBAccess() throws IOException {
        db = FirebaseService.getFireBaseDatabase();
        ref = db.getReference("users/");
    }

    public synchronized static DBAccess getInstance() throws Exception {
        if (instance == null){
            instance = new DBAccess();
        }
        return instance;
    }

    public void addFavoriteToUser(String uid, int movieId) throws ExecutionException, InterruptedException {
        DatabaseReference favRef = ref.child(uid + "/favorites");

        favRef.push().setValueAsync(movieId);
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");

        DBAccess.getInstance().addFavoriteToUser("1Pum18WS6YYWZtZz1KZYrlZAVgl1", 453395);
    }
}
