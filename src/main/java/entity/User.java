package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private final String username;
    private final String password;
    private final Map<String, TierList> tierLists;
    private final List<String> following;
    private final List<String> followers;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.tierLists = new HashMap<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
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

    public List<String> getFollowing() {
        return new ArrayList<>(following);
    }

    public List<String> getFollowers() {
        return new ArrayList<>(followers);
    }

    public void addFollowers (String user) {
        followers.add(user);
    }

    public void addFollowing(String user) {
        following.add(user);
    }

    public void removeFollowers (String user) {
        followers.remove(user);
    }

    public void removeFollowing(String user) {
        following.remove(user);
    }

}

