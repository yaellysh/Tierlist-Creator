package use_case.tierlist;

import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;

public interface TierListDataAccessInterface {

    void saveTier(User user, TierList tierList, Item item, Tier tier);
    

}
