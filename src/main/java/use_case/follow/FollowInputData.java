package use_case.follow;
import entity.User;

public class FollowInputData {
    final private boolean follow;
    final private String followerName;
    final private String userBeingFollowedName;

    public FollowInputData(String follower, String userBeingFollowed, boolean follow) {
        this.followerName = follower;
        this.userBeingFollowedName = userBeingFollowed;
        this.follow = follow;
    }

    public String getFollower() {
        return followerName;
    }

    public String getUserBeingFollowed() {
        return userBeingFollowedName;
    }
    public boolean getFollow() {
        return follow;
    }
}
