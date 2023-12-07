package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import entity.User;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.search_user.SearchUserDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.view_user.ViewUserDataAccessInterface;

import java.io.*;
import java.lang.reflect.Type;

import use_case.login.LoginDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class FileUserDataAccessObject implements SignupDataAccessInterface, LoginDataAccessInterface, TierListDataAccessInterface, FollowUserDataAccessInterface, ViewUserDataAccessInterface, SearchUserDataAccessInterface {

    private static final Type USER_MAP_TYPE = new TypeToken<Map<String, User>>() {}.getType();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Map<String, Integer> headers = new LinkedHashMap<>();

    private Map<String, User> accounts = new HashMap<>();

    private final Path path;
    private final Map<String, User> users;

    public FileUserDataAccessObject(String csvPath, Path path, Map<String, User> users) throws IOException {
        this.path = path;


        File csvFile = new File(csvPath);
        this.users = users;
        headers.put("username", 0);
        headers.put("password", 1);

        this.accounts = new HashMap<>();

        try {
            Reader reader = Files.newBufferedReader(this.path, StandardCharsets.UTF_8);
            List<User> accounts = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
            accounts.forEach(s -> this.accounts.put(s.getUsername(), s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    User user = new User(username, password);
                    accounts.put(username, user);
                }
            }
        }
    }



    public void save() {
        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(this.path, StandardCharsets.UTF_8);
            gson.toJson(this.accounts.values(), writer);
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
        return this.accounts.get(username);
    }

    @Override
    public void addUser(User user) {

        this.accounts.put(user.getUsername(), user);

        this.users.put(user.getUsername(), user);
        save();

    }

    public void updateUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources.json", StandardCharsets.UTF_8))) {
            gson.toJson(accounts, USER_MAP_TYPE, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON file", e);
        }
    }

    public void removeUser(String username) {
        this.users.remove(username);
        this.save();
    }

}
