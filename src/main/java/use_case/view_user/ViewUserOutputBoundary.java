package use_case.view_user;

import use_case.follow.FollowOutputData;

public interface ViewUserOutputBoundary {
    void prepareSuccessView(ViewUserOutputData viewUserOutputData);
}
