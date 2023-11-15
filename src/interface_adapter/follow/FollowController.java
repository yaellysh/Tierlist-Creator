package interface_adapter.follow;

import entity.User;
import use_case.follow.FollowInputBoundary;
import use_case.follow.FollowInputData;

public class FollowController {
    final FollowInputBoundary followUseCaseInteractor;

    public FollowController(FollowInputBoundary followInputBoundary) {
        followUseCaseInteractor = followInputBoundary;
    }

    public void execute(User follower, User userBeingFollowed) {
        FollowInputData inputData = new FollowInputData(follower, userBeingFollowed);
        followUseCaseInteractor.execute(inputData);
    }
}
