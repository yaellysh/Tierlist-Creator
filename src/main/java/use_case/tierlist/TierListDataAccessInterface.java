package use_case.tierlist;

import entity.Tier;
import entity.User;

public interface TierListDataAccessInterface {

    void saveTier(String user, String tierList, String item, Tier tier);

    User getUser(String username);
}
