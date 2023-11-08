package use_case.tierlist;

import entity.Tier;
import entity.User;

public class TierListInputData {

    private final User user;
    private final String tierList;

    public TierListInputData(User user, String tierList) {
        this.user = user;
        this.tierList = tierList;
    }



    public User getUser() {
        return user;
    }

    public String getTierList() {
        return tierList;
    }
}
