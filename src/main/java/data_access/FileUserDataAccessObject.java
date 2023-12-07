package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import entity.Item;
import entity.TierList;
import entity.User;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.search_user.SearchUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.view_user.ViewUserDataAccessInterface;

import java.io.*;
import java.lang.reflect.Type;

import use_case.login.LoginDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.view_user.ViewUserDataAccessInterface;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, TierListDataAccessInterface, FollowUserDataAccessInterface, ViewUserDataAccessInterface, SearchUserDataAccessInterface {

    private File csvFile;

    private static final Type USER_MAP_TYPE = new TypeToken<Map<String, User>>() {}.getType();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Map<String, Integer> headers = new LinkedHashMap<>();

    private Map<String, User> accounts = new HashMap<>();


public class FileUserDataAccessObject implements SignupDataAccessInterface, LoginDataAccessInterface, TierListDataAccessInterface, FollowUserDataAccessInterface, ViewUserDataAccessInterface {
  
    private final Path path;



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

        Item dummyItem = new Item("dummy");
        ArrayList<Item> dummyList = new ArrayList<>();
        dummyList.add(dummyItem);
        TierList dummyTl1 = new TierList("dummyTl", dummyList);
        TierList dummyTl2 = new TierList("dummyT2", dummyList);
        terry.addTierList(dummyTl1);
        terry.addTierList(dummyTl2);

        


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
=======
    private final Map<String, User> users;
>>>>>>> 039b7d6bc381297089f4e8d9e38f12016f21c55b

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
<<<<<<< HEAD
        this.accounts.put(user.getUsername(), user);
=======
        this.users.put(user.getUsername(), user);
        save();
>>>>>>> 039b7d6bc381297089f4e8d9e38f12016f21c55b
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
