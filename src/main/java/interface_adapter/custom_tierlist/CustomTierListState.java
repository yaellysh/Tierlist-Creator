package interface_adapter.custom_tierlist;

import entity.User;

import java.util.ArrayList;

public class CustomTierListState {
    public User user;
    public ArrayList<String> items;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public CustomTierListState(User user, ArrayList<String> items) {
        this.user = user;
        this.items = items;
    }
}
