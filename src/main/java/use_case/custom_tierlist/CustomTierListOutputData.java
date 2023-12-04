package use_case.custom_tierlist;

import entity.User;

public class CustomTierListOutputData {

    private final User user;
    private final String tierList;

    public CustomTierListOutputData(User user, String tierList) {
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
