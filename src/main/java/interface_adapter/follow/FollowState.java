package interface_adapter.follow;

import java.util.HashMap;
import java.util.List;

import entity.User;

public class FollowState {
    private HashMap<String, Integer> relatedUsers;
    private String follower;
    private String userBeingFollowed;
    private boolean isFollowing;

    public FollowState(FollowState copy) {
        this.relatedUsers = copy.relatedUsers;
    }

    //maybe temp
    public FollowState(String follower, String userBeingFollowed, boolean isFollowing) {
        this.follower = follower;
        this.userBeingFollowed = userBeingFollowed;
        this.isFollowing = isFollowing;
    }

    public FollowState() {}

    public HashMap<String, Integer> getRelatedUsers() {
        return relatedUsers;
    }

    public String getFollower() {
        return this.follower;
    }

    public boolean getIsFollowing() {
        return this.isFollowing;
    }

    public String getUserBeingFollowed() {
        return this.userBeingFollowed;
    }

    public void setRelatedUsers(HashMap<String, Integer> relatedUsers) {
        this.relatedUsers = relatedUsers;
    }

    public void setIsFollowing(boolean bool) {
        this.isFollowing = bool;
    }
}
