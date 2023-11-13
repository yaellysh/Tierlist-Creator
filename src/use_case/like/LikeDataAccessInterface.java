package use_case.like;

import entity.TierList;
import entity.User;

public interface LikeUserDataAccessInterface {
    void updateLikes(User user, TierList tierList);
}