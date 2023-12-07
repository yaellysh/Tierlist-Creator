package interface_adapter.follow;

import use_case.follow.FollowInputBoundary;
import use_case.follow.FollowInputData;

public class FollowController {
    final FollowInputBoundary followUseCaseInteractor;

    public FollowController(FollowInputBoundary followInputBoundary) {
        followUseCaseInteractor = followInputBoundary;
    }

    public void execute(String follower, String userBeingFollowed, boolean follow) {
        FollowInputData inputData = new FollowInputData(follower, userBeingFollowed, follow);
        followUseCaseInteractor.execute(inputData);
    }

    public void execute(String follower, String viewUser, String tierList) {
        followUseCaseInteractor.execute(new FollowInputData(follower, viewUser, tierList));
    }
}
