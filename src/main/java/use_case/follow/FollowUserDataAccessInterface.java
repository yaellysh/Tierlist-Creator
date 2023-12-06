package use_case.follow;

import entity.User;

public interface FollowUserDataAccessInterface {
    void updateFollowing(User user, String username, boolean follow);
    void updateFollowers(User follower, String username, boolean follow);
    User getUser(String username);
}
