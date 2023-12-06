package interface_adapter.custom_tierlist;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public class CustomTierListState {
    public User user;
    public String title;
    public Map<Integer, String> items;
    public String error = null;

    public CustomTierListState() {
        this.items = new HashMap<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getItems() {
        return items.values().toArray(new String[0]);
    }

    public void addItem(String item, Integer i) {
        this.items.put(i, item);
    }
}
