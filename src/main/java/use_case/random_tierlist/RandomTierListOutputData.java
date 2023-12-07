package use_case.random_tierlist;

import entity.User;

public class RandomTierListOutputData {

    private final User user;
    private final String tierList;

    public RandomTierListOutputData(User user, String tierList) {
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
