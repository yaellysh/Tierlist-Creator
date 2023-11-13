package use_case.follow;

import entity.User;

public interface FollowUserDataAccessInterface {
    void updateFollowing(User follower, User userBeingFollowed);
    void updateUserBeingFollowed(User follower, User userBeingFollowed);
}
