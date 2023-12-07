package use_case.follow;

import entity.User;

import java.util.HashMap;

public class FollowOutputData {


    // required variables
    private final int newFollowers;
    private final boolean follow;

    //optional variables
    private final HashMap<String, Integer> relatedUsers;
    private final String tierListName;
    private final User currentUser;
    private final User viewUser;

    private FollowOutputData(FollowOutputBuilder builder) {
        this.newFollowers = builder.newFollowers;
        this.follow = builder.follow;
        this.relatedUsers = builder.relatedUsers;
        this.currentUser = null;
        this.tierListName = null;
        this.viewUser = null;
    }

    public FollowOutputData(User currentUser, User viewUser, String tierListName) {
        this.currentUser = currentUser;
        this.viewUser = viewUser;
        this.tierListName = tierListName;
        this.newFollowers = 0;
        this.follow = false;
        this.relatedUsers = new HashMap<>();
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

    public String getTierListName() {
        return tierListName;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User getViewUser() {
        return viewUser;
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
