package entity;

import java.util.List;

public class User {

    private final String username;
    private List<TierList> tierLists;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<TierList> getTierLists() {
        return tierLists;
    }

    public void setTierLists(List<TierList> tierLists) {
        this.tierLists = tierLists;
    }
}
