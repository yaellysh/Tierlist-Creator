package use_case.follow;

import entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowOutputData {


    // required variables
    private final int newFollowers;
    private final boolean follow;

    //optional variables
    private final HashMap<String, Integer> relatedUsers;

    private FollowOutputData(FollowOutputBuilder builder) {
        this.newFollowers = builder.newFollowers;
        this.follow = builder.follow;
        this.relatedUsers = builder.relatedUsers;
    }

    // public getters for variables
    public HashMap<String, Integer> getRelatedUsers() {
        return relatedUsers;
    }
    public int getNewFollowers() {
        return newFollowers;
    }
    public boolean getFollow() {
        return follow;
    }

    public static class FollowOutputBuilder {
        // required variables
        private final int newFollowers;
        private final boolean follow;

        // optional variables
        private HashMap<String, Integer> relatedUsers = new HashMap<>();

        public FollowOutputBuilder(int newFollowers, boolean follow) {
            this.newFollowers = newFollowers;
            this.follow = follow;
        }

        public FollowOutputBuilder buildRelatedUsers(HashMap<String, Integer> relatedUsers) {
            this.relatedUsers = relatedUsers;
            return this;
        }

        public FollowOutputData build() {
            return new FollowOutputData(this);
        }

    }
}
