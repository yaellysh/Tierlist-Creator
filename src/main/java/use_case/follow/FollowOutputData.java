package use_case.follow;

import entity.User;

import java.util.HashMap;
import java.util.List;

public class FollowOutputData {
    private final HashMap<String, Integer> relatedUsers;

    public FollowOutputData(HashMap<String, Integer> relatedUsers) {
        this.relatedUsers = relatedUsers;
    }

    public HashMap<String, Integer> getRelatedUsers() {
        return relatedUsers;
    }
}
