package interface_adapter.view_existing;

import entity.User;

public class ViewExistingState {
    public User user;
    public String title;

    public ViewExistingState(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
