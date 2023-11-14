package use_case.follow;

import entity.User;

public class FollowInteractor implements FollowInputBoundary {
    final FollowUserDataAccessInterface userDataAccessObject;
    final FollowOutputBoundary followPresenter;

    public FollowInteractor(FollowUserDataAccessInterface userDataAccessInterface,
                            FollowOutputBoundary loginOutputBoundary) {
        userDataAccessObject = userDataAccessInterface;
        followPresenter = loginOutputBoundary;
    }

    public void execute(FollowInputData followInputData) {
        User follower = followInputData.getFollower();
        User userBeingFollowed = followInputData.getUserBeingFollowed();
        userDataAccessObject.updateFollowing(follower, userBeingFollowed);
        userDataAccessObject.updateUserBeingFollowed(follower, userBeingFollowed);

    }
}
