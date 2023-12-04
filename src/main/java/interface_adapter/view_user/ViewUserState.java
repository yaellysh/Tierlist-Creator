package interface_adapter.view_user;

import java.util.List;

public class ViewUserState {
    private List<String> tierLists;
    private int numFollowing;
    private int numFollowers;

    public ViewUserState(List<String> tierLists, int numFollowing, int numFollowers) {
        this.tierLists = tierLists;
        this.numFollowing = numFollowing;
        this.numFollowers = numFollowers;
    }

    public ViewUserState() {}

    public int getNumFollowing() {
        return numFollowing;
    }

    public int getNumFollowers() {
        return numFollowers;
    }

    public List<String> getTierLists() {
        return tierLists;
    }

    public void setNumFollowing(int numFollowing) {
        this.numFollowing = numFollowing;
    }

    public void setNumFollowers(int numFollowers) {
        this.numFollowers = numFollowers;
    }

    public void setTierLists(List<String> tierLists) {
        this.tierLists = tierLists;
    }
}
