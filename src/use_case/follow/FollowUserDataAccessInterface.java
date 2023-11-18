package use_case.follow;

import entity.User;

public interface FollowUserDataAccessInterface {
    void updateFollowing(User follower, String userBeingFollowed);
    void updateUserBeingFollowed(String follower, User userBeingFollowed);
}
