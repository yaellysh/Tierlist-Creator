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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUserDataAccessObject implements FollowUserDataAccessInterface{

    //private final Path path;

    private Map<String, User> users = new HashMap<String,User>();

    public FileUserDataAccessObject() {
        User tim = new User("lt_rui");
        User terry = new User("terryfufu");
        users.put("lt_rui", tim);
        users.put("terryfufu", terry);

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
    public void updateFollowing(User follower, String userBeingFollowed) {
        follower.addFollowing(userBeingFollowed);
    }

    @Override
    public void updateUserBeingFollowed(String follower, User userBeingFollowed) {
        userBeingFollowed.addFollower(follower);
    }
}