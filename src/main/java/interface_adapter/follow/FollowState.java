package interface_adapter.follow;

import java.util.HashMap;
import java.util.List;

import entity.User;

public class FollowState {
    private HashMap<String, Integer> relatedUsers;
    private String follower;
    private User userBeingFollowed;
    private boolean isFollowing;
    private List<String> tierLists;


    //maybe temp
    public FollowState(String follower, User userBeingFollowed, boolean isFollowing, List<String> tierLists) {
        this.follower = follower;
        this.userBeingFollowed = userBeingFollowed;
        this.isFollowing = isFollowing;
        this.tierLists = tierLists;
    }

    public FollowState() {}

    public List<String> getTierLists() {
        return tierLists;
    }

    public void setTierLists(List<String> tierLists) {
        this.tierLists = tierLists;
    }
//    public HashMap<String, Integer> getRelatedUsers() {
//        return relatedUsers;
//    }

    public String getFollower() {
        return this.follower;
    }

    public boolean getIsFollowing() {
        return this.isFollowing;
    }

    public User getUserBeingFollowed() {
        return this.userBeingFollowed;
    }

//    public void setRelatedUsers(HashMap<String, Integer> relatedUsers) {
//        this.relatedUsers = relatedUsers;
//    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public void setUserBeingFollowed(User userBeingFollowed) {
        this.userBeingFollowed = userBeingFollowed;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public void setIsFollowing(boolean bool) {
        this.isFollowing = bool;
    }
}
