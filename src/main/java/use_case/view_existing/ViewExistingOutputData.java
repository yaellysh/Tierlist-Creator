package use_case.view_existing;

import entity.User;

public class ViewExistingOutputData {

    private final User user;
    private final String tierList;

    public ViewExistingOutputData(User user, String tierList) {
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
