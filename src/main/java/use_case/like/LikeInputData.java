package use_case.like;

import entity.TierList;
import entity.User;

public class LikeInputData {
    private final User user;
    private final TierList tierList;

    public LikeInputData(User user, TierList tierList) { 
        this.user = user;
        this.tierList = tierList;
    }

    User getUser() {
        return this.user;
    }

    TierList getTierlist() {
        return this.tierList;
    }
}