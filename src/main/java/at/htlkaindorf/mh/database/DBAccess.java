package at.htlkaindorf.mh.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * <b>Movieholic</b><br><br>
 * Firestore Database Access Class.<br>
 * @author David, Simon
 * @version 1.0
 * @since last update: 10.06.2022
 */
public class DBAccess {

    private static DBAccess instance;

    private Firestore db;
    private CollectionReference userRef;

    private static String TRENDING = "trending";
    private static String NOW_PLAYING = "nowplaying";
    private static String TOP_RATED = "toprated";
    private static String UPCOMING = "upcoming";

    /**
     * Private Constructor, to prevent instantiation.
     * @return {@link DBAccess}
     * @throws IOException
     */
    private DBAccess() throws IOException {
        db = FirebaseService.getFireBaseDatabase();
        userRef = db.collection("users");
    }

    /**
     * Returns the singleton instance of the DBAccess class.
     * @return {@link DBAccess}
     * @throws Exception
     */
    public synchronized static DBAccess getInstance() throws Exception {
        if (instance == null) {
            instance = new DBAccess();
        }
        return instance;
    }

    /**
     * Adds a favorite movie to a specific user.
     * @param uid
     * @param movieId
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void addFavoriteToUser(String uid, int movieId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = userRef.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("favorites", FieldValue.arrayUnion(movieId));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }

    /**
     * Removes a favorite movie from a specific user.
     * @param uid
     * @param movieId
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void removeFavoriteFromUser(String uid, int movieId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = userRef.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("favorites", FieldValue.arrayRemove(movieId));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }

    /**
     * Creates homepage items of a specific user.
     * @param uid
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void createUserHomePageItems(String uid) throws ExecutionException, InterruptedException {
        Map<String, Boolean> homepage = new HashMap<>();
        homepage.put(TRENDING, true);
        homepage.put(NOW_PLAYING, true);
        homepage.put(TOP_RATED, true);
        homepage.put(UPCOMING, true);


        ApiFuture<WriteResult> future = db.collection("users").document(uid).update("homepage", homepage);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    /**
     * Updates the homepage items of a specific user.
     * @param uid
     * @param trending
     * @param nowplaying
     * @param toprated
     * @param upcoming
     * @throws ExecutionException
     * @throws InterruptedException
     */
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

    /**
     * Returns the homepage items of a specific user.
     * @param uid
     * @return {@link Map<String, Boolean>}
     * @throws ExecutionException
     * @throws InterruptedException
     */
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

    /**
     * Adds a movie to the watchlist of a specific user.
     * @param uid
     * @param movieID
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void addToWatchlist(String uid, int movieID) throws ExecutionException, InterruptedException {
        DocumentReference docRef = userRef.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("watchlist", FieldValue.arrayUnion(movieID));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }

    /**
     * Removes a movie from the watchlist of a specific user.
     * @param uid
     * @param movieID
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void removeFromWatchlist(String uid, int movieID) throws ExecutionException, InterruptedException {
        DocumentReference docRef = userRef.document(uid);
        ApiFuture<WriteResult> arrayUnion = docRef.update("watchlist", FieldValue.arrayRemove(movieID));
        System.out.println("Updated time: " + arrayUnion.get().getUpdateTime());
    }
}
