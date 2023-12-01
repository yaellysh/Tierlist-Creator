package interface_adapter.follow;

import java.util.List;

public class FollowState {
    private List<String> relatedUsers;
    private String follower;
    private String userBeingFollowed;

    public FollowState(FollowState copy) {
        relatedUsers = copy.relatedUsers;
    }

    public FollowState() {}

    public List<String> getRelatedUsers() {
        return relatedUsers;
    }

    public String getFollower() {
        return this.follower;
    }

    public String getUserBeingFollowed() {
        return this.userBeingFollowed;
    }

    public void setRelatedUsers(List<String> relatedUsers) {
        this.relatedUsers = relatedUsers;
    }
}
