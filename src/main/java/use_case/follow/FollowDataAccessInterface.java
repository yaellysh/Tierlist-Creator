package use_case.follow;

import entity.User;

public interface FollowDataAccessInterface {
    void save();
    User getUser(String username);

   }
