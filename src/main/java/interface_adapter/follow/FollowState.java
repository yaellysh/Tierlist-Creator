package interface_adapter.follow;

import java.util.List;

public class FollowState {
    private List<String> relatedUsers;


    public FollowState(FollowState copy) {
        relatedUsers = copy.relatedUsers;
    }

    public FollowState() {}

    public List<String> getRelatedUsers() {
        return relatedUsers;
    }

    public void setRelatedUsers(List<String> relatedUsers) {
        this.relatedUsers = relatedUsers;
    }
}
