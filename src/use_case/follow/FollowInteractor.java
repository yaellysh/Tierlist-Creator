package use_case.follow;

import entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FollowInteractor implements FollowInputBoundary {
    final FollowUserDataAccessInterface userDataAccessObject;
    final FollowOutputBoundary followPresenter;

    public FollowInteractor(FollowUserDataAccessInterface userDataAccessInterface,
                            FollowOutputBoundary loginOutputBoundary) {
        userDataAccessObject = userDataAccessInterface;
        followPresenter = loginOutputBoundary;
    }

    public void execute(FollowInputData followInputData) {
        // get usernames from input data
        String followerName = followInputData.getFollower();
        String userBeingFollowedName = followInputData.getUserBeingFollowed();

        // get User objects using usernames
        User follower = userDataAccessObject.getUser(followerName);
        User userBeingFollowed = userDataAccessObject.getUser(userBeingFollowedName);

        // update following and followers both in the entity objects and in the database
        // follower.addFollowing(userBeingFollowedName);
        // userBeingFollowed.addFollower(followerName);
        // can put these inside data access object
        userDataAccessObject.updateFollowing(follower, userBeingFollowedName);
        userDataAccessObject.updateUserBeingFollowed(followerName, userBeingFollowed);
        // want to find the users that most of my following also follow
        // "for example, people you know also follow ..."

        // Create the list of people you know
        List<String> followerFollowing = follower.getFollowing();

        // Create the list of the users that people you know could know
        List<String> userBeingFollowedFollowers = userBeingFollowed.getFollowers();
        List<String> userBeingFollowedFollowing = userBeingFollowed.getFollowing();
        userBeingFollowedFollowing.addAll(userBeingFollowedFollowers);
        List<String> userBeingFollowedRelatedUsers = new ArrayList<>(userBeingFollowedFollowing);
        Set<String> setRelatedUsers = new HashSet<>(userBeingFollowedRelatedUsers);

        String firstMutual;
        int firstMutualCount;
        String secondMutual;
        int secondMutualCount;
        String thirdMutual;
        int thirdMutualCount;
        for (String relatedUser : setRelatedUsers) {

        }


    }
}
