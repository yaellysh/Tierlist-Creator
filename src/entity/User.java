package entity;

import java.util.List;

public class User {

    private final String username;
    private final String password;
    private List<TierList> tierLists;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<TierList> getTierLists() {
        return tierLists;
    }

    public void setTierLists(List<TierList> tierLists) {
        this.tierLists = tierLists;
    }
}
