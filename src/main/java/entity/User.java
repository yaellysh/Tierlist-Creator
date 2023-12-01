package entity;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class User {

    private final String username;
    private final Map<String, TierList> tierLists;
    private List<String> following;
    private List<String> followers;

    public User(String username) {
        this.username = username;
        this.tierLists = new HashMap<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<TierList> getTierLists() {
        return new ArrayList<>(tierLists.values());
    }

//    public void addTierList(TierList list) {
//        this.tierLists.put(list.getName(), list);
//    }

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

    public void addFollower(String user) {
        followers.add(user);
    }

    public void addFollowing(String user) {
        following.add(user);
    }
}

