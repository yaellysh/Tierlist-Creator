package interface_adapter.follow;

import java.util.HashMap;
import java.util.List;

import entity.User;

public class FollowState {
    private HashMap<String, Integer> relatedUsers;
    private String follower;
    private String userBeingFollowed;

    public FollowState(FollowState copy) {
        this.relatedUsers = copy.relatedUsers;
    }

    public FollowState() {}

    public HashMap<String, Integer> getRelatedUsers() {
        return relatedUsers;
    }

    public String getFollower() {
        return this.follower;
    }

    public String getUserBeingFollowed() {
        return this.userBeingFollowed;
    }

    public void setRelatedUsers(HashMap<String, Integer> relatedUsers) {
        this.relatedUsers = relatedUsers;
    }
}
