package use_case.follow;

import entity.User;

import java.util.*;

public class FollowInteractor implements FollowInputBoundary {
    final FollowDataAccessInterface userDataAccessObject;
    final FollowOutputBoundary followPresenter;

    public FollowInteractor(FollowDataAccessInterface userDataAccessInterface, FollowOutputBoundary followOutputBoundary) {
        userDataAccessObject = userDataAccessInterface;
        followPresenter = followOutputBoundary;
    }

    public void execute(FollowInputData followInputData) {

        // get information from input data
        String followerName = followInputData.getFollower();
        String userBeingFollowedName = followInputData.getUserBeingFollowed();
        boolean follow = followInputData.getFollow();
        //System.out.println(follow + " this one");
        //System.out.println(followerName);

        // get User objects using usernames
        User follower = userDataAccessObject.getUser(followerName);
        User userBeingFollowed = userDataAccessObject.getUser(userBeingFollowedName);

        if (!follow) {
            // follow the user
            
            // get new follower count of the user being followed
            int newFollowerCount = userBeingFollowed.getFollowers().size() + 1;
            System.out.println(newFollowerCount + "at the start");

            List<String> followerFollowing = follower.getFollowing();

            FollowOutputData followOutputData = new FollowOutputData.FollowOutputBuilder(newFollowerCount, true)
                    .build();
            //System.out.println(tempy);
            follower.addFollowing(userBeingFollowedName);
            userBeingFollowed.addFollowers(followerName);
            userDataAccessObject.save();
            followPresenter.prepareSuccessView(followOutputData);
        } else {
            // unfollow the user
            int newFollowerCount = userBeingFollowed.getFollowers().size() - 1;


            follower.removeFollowing(userBeingFollowedName);
            userBeingFollowed.removeFollowers(followerName);
            // change the entities


            userDataAccessObject.save();

            FollowOutputData followOutputData = new FollowOutputData.FollowOutputBuilder(newFollowerCount, false)
                    .build();
            followPresenter.prepareSuccessView(followOutputData);
        }

    }

//    private HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
//    {
//        // Create a list from elements of HashMap
//        List<Map.Entry<String, Integer> > list
//            = new LinkedList<Map.Entry<String, Integer> >(
//                hm.entrySet());
//
//        // Sort the list using lambda expression
//        Collections.sort(
//            list,
//            (i1,
//             i2) -> i2.getValue().compareTo(i1.getValue()));
//
//        // put data from sorted list to hashmap
//        HashMap<String, Integer> temp
//            = new LinkedHashMap<String, Integer>();
//        for (Map.Entry<String, Integer> aa : list) {
//            temp.put(aa.getKey(), aa.getValue());
//        }
//        return temp;
//    }
}
