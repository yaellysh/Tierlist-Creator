package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.*;
import use_case.tierlist.TierListDataAccessInterface;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUserDataAccessObject implements TierListDataAccessInterface {

    private final Map<String, User> users;

    public FileUserDataAccessObject(String path) {
        this.users = new HashMap<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8);
            List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
            users.forEach(s -> this.users.put(s.getUsername(), s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FileUserDataAccessObject(Map<String, User> users) {
        this.users = users;
    }

    public static void main(String[] args) {

//        Item item1 = new Item("item1");
//        Item item2 = new Item("item2");
//
//        item1.setTier(Tier.A);
//        item2.setTier(Tier.B);
//
//        TierList list = new TierList("tierlist", List.of(item1, item2));
//
//        User user = new User("jillibean");
//        user.addTierList(list);
//
//        Map<String, User> users = new HashMap<>();
//        users.put("jillibean", user);
//
//        FileUserDataAccessObject object = new FileUserDataAccessObject(users);
//        object.save();

        FileUserDataAccessObject object = new FileUserDataAccessObject("users.json");
        List<User> users = object.users.values().stream().toList();
        System.out.println(users.get(0));
        System.out.println(users.get(0).getTierList("tierlist").getItem("item1").getTier());

    }

    public void save() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/users.json"), StandardCharsets.UTF_8);
            gson.toJson(this.users.values(), writer);
            writer.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveTier(String user, String tierList, String item, Tier tier) {
        this.users.get(user)
                .getTierList(tierList)
                .getItem(item)
                .setTier(tier);
        this.save();
    }
}
