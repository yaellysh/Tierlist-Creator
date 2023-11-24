package use_case.like;

import entity.TierList;
import entity.User;

public interface LikeDataAccessInterface {
    void updateLikes(User user, TierList tierList);
}