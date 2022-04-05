package at.htlkaindorf.mh.database;

import at.htlkaindorf.mh.beans.User;
import io.github.rctcwyvrn.blake3.Blake3;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserDB {
    private static UserDB theInstance;
    private List<User> userList = new ArrayList<>();

    public static UserDB getInstance(){
        if (theInstance == null){
            theInstance = new UserDB();
        }
        return theInstance;
    }

    public User login(User user){
        Blake3 hasher = Blake3.newInstance();
        hasher.update(user.getPassword().getBytes());
        String hexhash = hasher.hexdigest();

        User loginUser = new User(user.getEmail(), hexhash, 'u');

        if (!validateUser(loginUser)){
            throw new NoSuchElementException();
        }

        return loginUser;
    }

    public boolean validateUser(User user){
        return userList.contains(user);
    }

    public User register(User user){
        Optional<User> u = findUserByEmail(user.getEmail());
        User rUser;
        if (u.isPresent()){
            throw new KeyAlreadyExistsException();
        }else{
            Blake3 hasher = Blake3.newInstance();
            hasher.update(user.getPassword().getBytes());
            String hexhash = hasher.hexdigest();

            rUser = new User(user.getEmail(), hexhash, 'u');
            userList.add(rUser);
        }
        return rUser;
    }

    public Optional<User> findUserByEmail(String email){
        return userList.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }
}