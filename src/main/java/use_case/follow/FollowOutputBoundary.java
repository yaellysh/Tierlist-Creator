package use_case.follow;

import use_case.view_user.ViewUserOutputData;

public interface FollowOutputBoundary {
    void prepareSuccessView(FollowOutputData followOutputData);
}
