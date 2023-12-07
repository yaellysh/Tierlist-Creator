package interface_adapter.selector;

import entity.User;

public class SelectorState {
    private User user;

    public SelectorState() {}
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
