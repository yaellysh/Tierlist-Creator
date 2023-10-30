package data_access;

import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;
import use_case.tierlist.TierListDataAccessInterface;

import java.util.Map;

public class FileUserDataAccessObject implements TierListDataAccessInterface {

    private Map<User, TierList> userTierListMap;

    @Override
    public void saveTier(User user, TierList tierList, Item item, Tier tier) {

    }
}
