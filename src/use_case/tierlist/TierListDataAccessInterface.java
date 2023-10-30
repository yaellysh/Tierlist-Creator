package use_case.tierlist;

import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;

public interface TierListDataAccessInterface {

    void saveTier(String user, String tierList, String item, Tier tier);


}
