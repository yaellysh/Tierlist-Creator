package use_case.follow;

import entity.User;

import java.util.*;

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
        // ** follower.addFollowing(userBeingFollowedName);
        // ** userBeingFollowed.addFollower(followerName);
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

        TreeMap<String, Integer> mutualsMap = new TreeMap<>();

        // iterate through the users related to the user you just followed
        for (String relatedUser : setRelatedUsers) {
            int mutualsCount = 0;

            // iterate through the users you follow
            for (String usernameYouFollow : followerFollowing) {
                User userYouFollow = userDataAccessObject.getUser(usernameYouFollow);
                List<String> userYouFollowFollowing = userYouFollow.getFollowing();

                // iterate through the following of the user you follow
                for  (String usernameYouFollowFollowing : userYouFollowFollowing) {
                    if (usernameYouFollowFollowing.equals(relatedUser)) {
                        mutualsCount ++;
                    }
                }

            }

            // at this point, we have determined the mutuals count of the current related user
            // the following code uses the mutuals count to keep track of the users with the highest mutuals count
            if (mutualsMap.size() < 3) {
                mutualsMap.put(relatedUser, mutualsCount);
                mutualsMap = new TreeMap<>(Comparator.comparingInt(mutualsMap::get));
            }

            else {
                for (Map.Entry<String, Integer> mutualUser : mutualsMap.entrySet()) {
                    String mutualUsername = mutualUser.getKey();
                    Integer mutualCount = mutualUser.getValue();
                    if (mutualCount < mutualsCount) {
                        mutualsMap.remove(mutualUsername);
                        mutualsMap.put(relatedUser, mutualsCount);
                    }
                }
            }
        }
        List<String> relatedUsers = new ArrayList<>(mutualsMap.keySet());
        FollowOutputData followOutputData = new FollowOutputData(relatedUsers);
        followPresenter.prepareSuccessView(followOutputData);
    }
}
