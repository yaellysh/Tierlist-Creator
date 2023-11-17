package use_case.follow;
import entity.User;

public class FollowInputData {
    final private String followerName;
    final private String userBeingFollowedName;

    public FollowInputData(String follower, String userBeingFollowed) {
        this.followerName = follower;
        this.userBeingFollowedName = userBeingFollowed;
    }

    public String getFollower() {
        return followerName;
    }

    public String getUserBeingFollowed() {
        return userBeingFollowedName;
    }
}
