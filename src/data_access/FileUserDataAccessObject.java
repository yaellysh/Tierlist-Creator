package data_access;

import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;
import use_case.tierlist.TierListDataAccessInterface;

import java.util.Map;

public class FileUserDataAccessObject implements TierListDataAccessInterface {

    private Map<String, User> users;

    @Override
    public void saveTier(String user, String tierList, String item, Tier tier) {
        this.users.get(user)
                .getTierList(tierList)
                .getItem(item)
                .setTier(tier);
    }
}
