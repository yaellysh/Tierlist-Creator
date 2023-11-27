package use_case.follow;

import entity.User;

import java.util.HashMap;
import java.util.List;

public class FollowOutputData {
    private final HashMap<String, Integer> relatedUsers;
    private final int newFollowers;

    private final boolean follow;

    public FollowOutputData(HashMap<String, Integer> relatedUsers, int userFollowers, boolean follow) {
        this.relatedUsers = relatedUsers;
        this.newFollowers = userFollowers;
        this.follow = follow;
    }


    public HashMap<String, Integer> getRelatedUsers() {
        return relatedUsers;
    }

    public int getNewFollowers() {
        return newFollowers;
    }
    public boolean getFollow() {
        return follow;
    }
}
