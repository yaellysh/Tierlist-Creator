package use_case.tierlist;

import entity.User;

public interface TierListDataAccessInterface {

    void save();

    User getUser(String username);
}
