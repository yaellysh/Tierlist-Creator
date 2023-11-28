package use_case.selector;

import entity.User;

public class SelectorOutputData {

    private final User user;
    private final String label;

    public SelectorOutputData(String label, User user) {
        this.label = label;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getLabel() {
        return label;
    }
}
