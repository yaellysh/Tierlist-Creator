package use_case.follow;

import entity.User;

import java.util.List;

public class FollowOutputData {
    private final List<User> relatedUsers;

    public FollowOutputData(List<User> relatedUsers) {
        this.relatedUsers = relatedUsers;
    }

    public List<User> getRelatedUsers() {
        return relatedUsers;
    }
}
