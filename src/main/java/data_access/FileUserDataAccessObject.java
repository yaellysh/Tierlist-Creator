package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.*;
import use_case.tierlist.TierListDataAccessInterface;

import java.io.File;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUserDataAccessObject implements TierListDataAccessInterface {

    private final File jsonFile;

    private final Map<String, User> users;

    public FileUserDataAccessObject(String path) {
        jsonFile = new File(path);

        // TODO: Implement JSON reading

        this.users = new HashMap<>();
    }

    private FileUserDataAccessObject(Map<String, User> users) {
        jsonFile = new File("users.json");
        this.users = users;
    }

    public static void main(String[] args) {

        Item item1 = new Item("item1");
        Item item2 = new Item("item2");

        item1.setTier(Tier.A);
        item2.setTier(Tier.B);

        TierList list = new TierList("tierlist", List.of(item1, item2));

        User user = new User("jillibean");
        user.addTierList(list);

        Map<String, User> users = new HashMap<>();
        users.put("jillibean", user);

        FileUserDataAccessObject object = new FileUserDataAccessObject(users);
        object.save();

    }

    public void save() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Writer writer = Files.newBufferedWriter(Paths.get("users.json"), StandardCharsets.UTF_8);

            gson.toJson(this.users.values(), writer);

            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTier(String user, String tierList, String item, Tier tier) {
        this.users.get(user)
                .getTierList(tierList)
                .getItem(item)
                .setTier(tier);
    }
}
