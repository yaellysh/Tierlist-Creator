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
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUserDataAccessObject implements SignupDataAccessInterface, LoginDataAccessInterface, TierListDataAccessInterface, FollowUserDataAccessInterface, ViewUserDataAccessInterface {
  
    private final Path path;
    private Gson gson;

    private final Map<String, User> users;
    private static final Type USER_MAP_TYPE = new TypeToken<Map<String, User>>() {}.getType();

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
    public User getUser(String username) {
        return this.users.get(username);
    }

    @Override
    public void addUser(User user) {
        this.users.put(user.getUsername(), user);
        save();
    }


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