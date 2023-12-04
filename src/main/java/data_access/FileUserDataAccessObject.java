package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Tier;
import entity.TierList;
import entity.User;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUserDataAccessObject implements FollowUserDataAccessInterface{

    //private final Path path;

    private Map<String, User> users = new HashMap<String,User>();

    public FileUserDataAccessObject() {
        // D, E, F => A, E, F => B, F => C.
        User userA = new User("User A");
        User userB = new User("User B");
        User userC = new User("User C");
        User userD = new User("User D");
        User userE = new User("User E");
        User userF = new User("User F");
        userA.addFollowing("lt_rui");
        userB.addFollowing("lt_rui");
        userC.addFollowing("lt_rui");

        userD.addFollowing("User A");
        userE.addFollowing("User A");
        userF.addFollowing("User A");

        userE.addFollowing("User B");
        userF.addFollowing("User B");

        userF.addFollowing("User C");

        User tim = new User("lt_rui");
        User terry = new User("terryfufu");

        tim.addFollowing("User A");
        tim.addFollowing("User B");
        tim.addFollowing("User C");

        tim.addFollowers("User A");
        tim.addFollowers("User B");
        tim.addFollowers("User C");

        terry.addFollowing("User D");
        terry.addFollowing("User E");
        terry.addFollowing("User F");

        users.put("lt_rui", tim);
        users.put("terryfufu", terry);
        users.put("User A", userA);
        users.put("User B", userB);
        users.put("User C", userC);
        users.put("User D", userD);
        users.put("User E", userE);
        users.put("User F", userF);

        /*
        this.path = Paths.get(path);
        this.users = new HashMap<>();

        try {
            Reader reader = Files.newBufferedReader(this.path, StandardCharsets.UTF_8);
            List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
            users.forEach(s -> this.users.put(s.getUsername(), s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

    }

    @Override
    public User getUser(String username) {
        return this.users.get(username);
    }

    // This will be overridden as a part of the signup DAI
    public void addUser(User user) {
        this.users.put(user.getUsername(), user);
    }

    @Override
    public void updateFollowing(User user, String username, boolean follow) {
        user.addFollowing(username);
    }

    @Override
    public void updateFollowers(User follower, String username, boolean follow) {
        getUser(username).addFollowers(follower.getUsername());
    }
}