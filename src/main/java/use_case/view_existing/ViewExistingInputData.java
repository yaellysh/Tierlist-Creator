package use_case.view_existing;

import entity.User;

public class ViewExistingInputData {

    private final User user;
    private final String tierList;

    public ViewExistingInputData(User user, String tierList) {
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
