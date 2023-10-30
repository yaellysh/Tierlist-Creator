package data_access;

import entity.Tier;
import entity.User;
import use_case.tierlist.TierListDataAccessInterface;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileUserDataAccessObject implements TierListDataAccessInterface {

    private final File jsonFile;

    private final Map<String, User> users;

    public FileUserDataAccessObject(String path) {
        jsonFile = new File(path);

        // TODO: Implement JSON reading

        this.users = new HashMap<>();
    }

    @Override
    public void saveTier(String user, String tierList, String item, Tier tier) {
        this.users.get(user)
                .getTierList(tierList)
                .getItem(item)
                .setTier(tier);
    }
}
