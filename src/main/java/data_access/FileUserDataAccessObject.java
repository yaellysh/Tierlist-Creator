package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.User;
import factory.UserFactory;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.search_user.SearchUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.view_user.ViewUserDataAccessInterface;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, TierListDataAccessInterface, FollowUserDataAccessInterface, ViewUserDataAccessInterface, SearchUserDataAccessInterface {

    private File csvFile;

    private static final Type USER_MAP_TYPE = new TypeToken<Map<String, User>>() {}.getType();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Map<String, Integer> headers = new LinkedHashMap<>();

    private Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;
  
    private Path path;


    //temp
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

        accounts.put("lt_rui", tim);
        accounts.put("terryfufu", terry);
        accounts.put("User A", userA);
        accounts.put("User B", userB);
        accounts.put("User C", userC);
        accounts.put("User D", userD);
        accounts.put("User E", userE);
        accounts.put("User F", userF);

        


    }

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);

        

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
                    User user = userFactory.create(username, password);
                    accounts.put(username, user);
                }
            }
        }
    }

    public FileUserDataAccessObject(String path) {
        this.path = Paths.get(path);
        this.accounts = new HashMap<>();

        try {
            Reader reader = Files.newBufferedReader(this.path, StandardCharsets.UTF_8);
            List<User> accounts = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
            accounts.forEach(s -> this.accounts.put(s.getUsername(), s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
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

    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public User getUser(String username) {
        return this.accounts.get(username);
    }

    // This will be overridden as a part of the signup DAI
    public void addUser(User user) {
        this.accounts.put(user.getUsername(), user);
    }

    public void updateUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources.json", StandardCharsets.UTF_8))) {
            gson.toJson(accounts, USER_MAP_TYPE, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON file", e);
        }
    }
}
