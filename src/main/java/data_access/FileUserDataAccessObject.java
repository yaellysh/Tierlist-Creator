package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Tier;
import entity.TierList;
import entity.User;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.view_user.ViewUserDataAccessInterface;

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

public class FileUserDataAccessObject implements TierListDataAccessInterface, FollowUserDataAccessInterface, ViewUserDataAccessInterface {

    private final Path path;

    private final Map<String, User> users;

    public FileUserDataAccessObject(String path) {
        this.path = Paths.get(path);
        this.users = new HashMap<>();

        try {
            Reader reader = Files.newBufferedReader(this.path, StandardCharsets.UTF_8);
            List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
            users.forEach(s -> this.users.put(s.getUsername(), s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(this.path, StandardCharsets.UTF_8);
            gson.toJson(this.users.values(), writer);
            writer.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        if (!follow) {
            user.addFollowing(username);
        }
        else {
            user.removeFollowing(username);
        }
    }

    @Override
    public void updateFollowers(User follower, String username, boolean follow) {
        
    }

}
