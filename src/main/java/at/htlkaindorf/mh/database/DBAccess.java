package at.htlkaindorf.mh.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class DBAccess {

    private static DBAccess instance;

    private Firestore db;
    private CollectionReference userRef;

    private static String TRENDING = "trending";
    private static String NOW_PLAYING = "nowplaying";
    private static String TOP_RATED = "toprated";
    private static String UPCOMING = "upcoming";

    private DBAccess() throws IOException {
        db = FirebaseService.getFireBaseDatabase();
        userRef = db.collection("users");
    }

    public synchronized static DBAccess getInstance() throws Exception {
        if (instance == null) {
            instance = new DBAccess();
        }
        return instance;
    }

    public void addFavoriteToUser(String uid, int movieId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = userRef.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("favorites", FieldValue.arrayUnion(movieId));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }

    public void removeFavoriteFromUser(String uid, int movieId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = userRef.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("favorites", FieldValue.arrayRemove(movieId));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }

    public void createUserHomePageItems(String uid) throws ExecutionException, InterruptedException {
        Map<String, Boolean> homepage = new HashMap<>();
        homepage.put(TRENDING, true);
        homepage.put(NOW_PLAYING, true);
        homepage.put(TOP_RATED, true);
        homepage.put(UPCOMING, true);


        ApiFuture<WriteResult> future = db.collection("users").document(uid).update("homepage", homepage);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    public void updateUserHomePageItems(String uid, boolean trending, boolean nowplaying,
                                        boolean toprated, boolean upcoming) throws ExecutionException, InterruptedException {
        Map<String, Boolean> homepage = new HashMap<>();
        homepage.put(TRENDING, trending);
        homepage.put(NOW_PLAYING, nowplaying);
        homepage.put(TOP_RATED, toprated);
        homepage.put(UPCOMING, upcoming);

        ApiFuture<WriteResult> future = db.collection("users").document(uid).update("homepage", homepage);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    public Map<String, Boolean> getUserHomePageItems(String uid) throws ExecutionException, InterruptedException {
        Map<String, Boolean> homePageItems = new HashMap<>();
        DocumentReference docRef = userRef.document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        Object o = Objects.requireNonNull(future.get().getData()).get("homepage");
        String homepageItemsStr = o.toString().substring(1, o.toString().length() - 1);
        String[] items = homepageItemsStr.split(",");

        for (String item : items) {
            String[] split = item.split("=");
            homePageItems.put(split[0].trim(), Boolean.valueOf(split[1].trim()));
        }

        System.out.println(homePageItems);
        return homePageItems;
    }

    public void addToWatchlist(String uid, int movieID) throws ExecutionException, InterruptedException {
        DocumentReference docRef = userRef.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("watchlist", FieldValue.arrayUnion(movieID));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }

    public void removeFromWatchlist(String uid, int movieID) throws ExecutionException, InterruptedException {
        DocumentReference docRef = userRef.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("watchlist", FieldValue.arrayRemove(movieID));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }


    public static void main(String[] args) throws Exception {
        System.setProperty("log4j.configurationFile", "./path_to_the_log4j2_config_file/log4j2.xml");

        //DBAccess.getInstance().getUserHomePageItems("1Pum18WS6YYWZtZz1KZYrlZAVgl1");
        //DBAccess.getInstance().updateUserHomePageItems("1Pum18WS6YYWZtZz1KZYrlZAVgl1", true, true, true, false);

        DBAccess.getInstance().addFavoriteToUser("1Pum18WS6YYWZtZz1KZYrlZAVgl1", 453394);
        DBAccess.getInstance().removeFavoriteFromUser("1Pum18WS6YYWZtZz1KZYrlZAVgl1", 453393);


    }


}
