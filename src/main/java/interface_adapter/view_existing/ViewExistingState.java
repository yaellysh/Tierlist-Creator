package interface_adapter.view_existing;

import entity.User;

public class ViewExistingState {
    public User user;
    public String title;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ViewExistingState(User user, String title) {
        this.user = user;
        this.title = title;
    }
}
