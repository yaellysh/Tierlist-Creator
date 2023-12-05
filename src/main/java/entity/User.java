package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private final String username;
    private final String password;
    private Map<String, TierList> tierLists;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.tierLists = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
