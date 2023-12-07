package use_case.follow;

public class FollowInputData {
    final private boolean follow;
    final private String followerName;
    final private String userBeingFollowedName;
    final private String tierListName;

    public FollowInputData(String follower, String userBeingFollowed, boolean follow) {
        this.followerName = follower;
        this.userBeingFollowedName = userBeingFollowed;
        this.follow = follow;
        tierListName = null;
    }

    public FollowInputData(String followerName, String userBeingFollowed, String tierListName) {
        this.followerName = followerName;
        this.userBeingFollowedName = userBeingFollowed;
        this.follow = false;
        this.tierListName = tierListName;
    }

    public String getFollower() {
        return followerName;
    }

    public String getTierListName() {
        return tierListName;
    }

    public String getUserBeingFollowed() {
        return userBeingFollowedName;
    }
    public boolean getFollow() {
        return follow;
    }
}
