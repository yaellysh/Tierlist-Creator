package use_case.selector;

import entity.User;

public class SelectorInputData {

    private final User user;

    public SelectorInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
