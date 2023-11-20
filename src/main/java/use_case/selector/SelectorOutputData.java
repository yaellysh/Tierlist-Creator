package use_case.selector;

import entity.User;

public class SelectorOutputData {

    private final User user;

    public SelectorOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
