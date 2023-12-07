package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.User;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.search_user.SearchUserDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.view_user.ViewUserDataAccessInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
<<<<<<< HEAD
import java.lang.reflect.Type;
=======
>>>>>>> a45df5bdc92d19958134a3eb1c9eccf6a5b160f3
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD
public class FileUserDataAccessObject implements SignupDataAccessInterface, LoginDataAccessInterface, TierListDataAccessInterface, FollowUserDataAccessInterface, ViewUserDataAccessInterface {
  
    private final Path path;
    private Gson gson;

    private final Map<String, User> users;
    private static final Type USER_MAP_TYPE = new TypeToken<Map<String, User>>() {}.getType();
=======
public class FileUserDataAccessObject implements SignupDataAccessInterface, LoginDataAccessInterface, TierListDataAccessInterface, ViewUserDataAccessInterface, SearchUserDataAccessInterface, FollowUserDataAccessInterface {
    private final Path path;
    private final Map<String, User> users;
>>>>>>> a45df5bdc92d19958134a3eb1c9eccf6a5b160f3

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

    public void save() {
        try {
             
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(this.path, StandardCharsets.UTF_8);
            gson.toJson(this.users.values(), writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }
  
    @Override
    public void updateUsers() {

    }

    @Override
    public User getUser(String username) {
        return this.users.get(username);
    }

    @Override
    public void addUser(User user) {
        this.users.put(user.getUsername(), user);
        save();
    }
<<<<<<< HEAD
    
=======

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
>>>>>>> a45df5bdc92d19958134a3eb1c9eccf6a5b160f3

    public void removeUser(String username) {
        this.users.remove(username);
        this.save();
    }

    @Override
    public void updateUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources.json", StandardCharsets.UTF_8))) {
            gson.toJson(users, USER_MAP_TYPE, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON file", e);
        }
    }

}