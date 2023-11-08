package use_case.tierlist;

import entity.Tier;
import entity.User;

public interface TierListDataAccessInterface {

    void saveTier(User user, String tierList);
}
