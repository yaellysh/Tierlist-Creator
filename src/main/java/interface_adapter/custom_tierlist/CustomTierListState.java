package interface_adapter.custom_tierlist;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public class CustomTierListState {
    public User user;
    public String title;
    public Map<Integer, String> items;

    public User getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getItems() {
        return items.values().toArray(new String[0]);
    }

    public void addItem(String item, Integer i) {
        this.items.put(i, item);

    }

    public CustomTierListState(User user) {
        this.user = user;
        this.items = new HashMap<>();
    }
}
