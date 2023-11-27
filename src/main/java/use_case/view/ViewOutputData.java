package use_case.view;

import entity.User;

public class ViewOutputData {

    private final User user;
    private final String tierList;

    public ViewOutputData(User user, String tierList) {
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
