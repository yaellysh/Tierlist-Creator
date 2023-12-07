package use_case.follow;

import entity.User;

public interface FollowUserDataAccessInterface {
    void updateUsers();
    User getUser(String username);

    void updateFollowing(User user, String username, boolean follow);

    void updateFollowers(User follower, String username, boolean follow);
}
