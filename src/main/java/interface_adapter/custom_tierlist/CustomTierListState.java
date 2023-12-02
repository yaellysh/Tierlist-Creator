package interface_adapter.custom_tierlist;

import entity.User;

import java.util.ArrayList;

public class CustomTierListState {
    public User user;
    public ArrayList<String> items;
    public String title;

    public User getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void addItem(String item) {
        System.out.println("ahhh");
        System.out.println(items);
        this.items.add(item);
    }

    public CustomTierListState(User user) {
        this.user = user;
        this.items = new ArrayList<>();
    }
}
