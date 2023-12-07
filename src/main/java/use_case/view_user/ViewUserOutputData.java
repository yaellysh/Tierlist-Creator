package use_case.view_user;

import java.util.List;

public class ViewUserOutputData {

    private final String username;
    private final List<String> tierLists;
    private final int numFollowers;
    private final int numFollowing;

    public ViewUserOutputData(String username, List<String> tierLists, int numFollowers, int numFollowing) {
        this.tierLists = tierLists;
        this.username = username;
        this.numFollowers = numFollowers;
        this.numFollowing = numFollowing;
    }

    public int getNumFollowers() {
        return numFollowers;
    }

    public int getNumFollowing() {
        return numFollowing;
    }

    public List<String> getTierLists() {
        return tierLists;
    }

    public String getUsername() {
        return username;
    }

}
