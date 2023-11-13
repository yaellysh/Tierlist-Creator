package use_case.follow;
import entity.User;

public class FollowInputData {
    final private User follower;
    final private User userBeingFollowed;

    public FollowInputData(User follower, User userBeingFollowed) {
        this.follower = follower;
        this.userBeingFollowed = userBeingFollowed;
    }

    public User getFollower() {
        return follower;
    }

    public User getUserBeingFollowed() {
        return userBeingFollowed;
    }
}
