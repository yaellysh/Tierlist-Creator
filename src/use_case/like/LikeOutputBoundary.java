package use_case.like;

import java.util.List;

import entity.TierList;
import entity.User;

public interface LikeOutputBoundary {
    void prepareSuccessView(List<TierList> tierLists);
}