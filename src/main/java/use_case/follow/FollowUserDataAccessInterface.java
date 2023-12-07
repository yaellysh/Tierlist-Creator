package use_case.follow;

import entity.User;

public interface FollowUserDataAccessInterface {
    void updateUsers();
    User getUser(String username);
}
