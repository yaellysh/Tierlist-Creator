package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private final String username;
    private final Map<String, TierList> tierLists;

    public User(String username) {
        this.username = username;
        this.tierLists = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public List<TierList> getTierLists() {
        return new ArrayList<>(tierLists.values());
    }

    public void addTierList(TierList list) {
        this.tierLists.put(list.getName(), list);
    }

    public TierList getTierList(String name) {
        return this.tierLists.get(name);
    }

    @Override
    public String toString() {
        return this.username;
    }

}
