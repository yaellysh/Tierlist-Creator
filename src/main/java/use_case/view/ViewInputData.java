package use_case.view;

import entity.User;

public class ViewInputData {

    private final User user;
    private final String tierList;

    public ViewInputData(User user, String tierList) {
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
