package use_case.selector;

import entity.User;

public class SelectorInputData {

    private final String label;
    private final User user;

    public SelectorInputData(String label, User user) {
        this.user = user;
        this.label = label;
    }

    public User getUser() {
        return user;
    }
}
