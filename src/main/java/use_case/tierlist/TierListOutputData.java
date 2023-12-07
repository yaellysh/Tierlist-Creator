package use_case.tierlist;

import entity.User;

public class TierListOutputData {

    private final User user;
    private final String tierList;

    public TierListOutputData(User user, String tierList) {
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
