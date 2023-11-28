package interface_adapter.random_tierlist;

import entity.User;

public class RandomTierListState {
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

    public RandomTierListState(User user, String title){
        this.user = user;
        this.title = title;
    }
}
