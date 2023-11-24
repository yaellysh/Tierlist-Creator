package use_case.follow;

import entity.User;

import java.util.List;

public class FollowOutputData {
    private final List<String> relatedUsers;

    public FollowOutputData(List<String> relatedUsers) {
        this.relatedUsers = relatedUsers;
    }

    public List<String> getRelatedUsers() {
        return relatedUsers;
    }
}
