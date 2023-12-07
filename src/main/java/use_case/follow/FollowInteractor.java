package use_case.follow;

import entity.User;

import java.util.*;

public class FollowInteractor implements FollowInputBoundary {
    final FollowUserDataAccessInterface userDataAccessObject;
    final FollowOutputBoundary followPresenter;

    public FollowInteractor(FollowUserDataAccessInterface userDataAccessInterface, FollowOutputBoundary followOutputBoundary) {
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

            // update following and followers both in the entity objects and in the database
            // change the entities



            // now we want to find the users that most of my following also follow
            // "for example, people you know also follow ..."

            // Create the list of people you know
            List<String> followerFollowing = follower.getFollowing();

            // Create the list of the users that people you know could know
            List<String> userBeingFollowedFollowers = userBeingFollowed.getFollowers();
            List<String> userBeingFollowedFollowing = userBeingFollowed.getFollowing();
            userBeingFollowedFollowing.addAll(userBeingFollowedFollowers);
            List<String> userBeingFollowedRelatedUsers = new ArrayList<>(userBeingFollowedFollowing);
            Set<String> setRelatedUsers = new HashSet<>(userBeingFollowedRelatedUsers);

            //list ppl they follow
            //find ppl in that list that are also followed by your followers
            //for ppl in that list
            //get mutual count, store in a hashmap user: mutual count
            //for ppl in my following, if person in ppls followers, mutaual += 1
            //sort hashmap by value, decreasing
            //return top 3

            TreeMap<String, Integer> mutualsMap = new TreeMap<>();

            HashMap<String, Integer> userMutualsCount = new HashMap<String, Integer>();

            // iterate through the users related to the user you just followed
            for (String relatedUser : setRelatedUsers) {
                //System.out.println(relatedUser);
                User relatedUserObject = userDataAccessObject.getUser(relatedUser);
                if (relatedUserObject.getFollowers().contains(followerName)){
                    continue;
                }
                int mutualsCount = 0;

                // iterate through the users you follow
                for (String usernameYouFollow : followerFollowing) {
                    User userYouFollow = userDataAccessObject.getUser(usernameYouFollow);
                    List<String> userYouFollowFollowing = userYouFollow.getFollowing();
//                    System.out.println(usernameYouFollow);

                    // iterate through the following of the user you follow
                    for  (String usernameYouFollowFollowing : userYouFollowFollowing) {
                        if (usernameYouFollowFollowing.equals(relatedUser)) {
                            //System.out.println("ok");
                            
                            mutualsCount++;
                        }
                    }

                }

                userMutualsCount.put(relatedUser, mutualsCount);
                //System.out.println(mutualsCount);

            /*
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
            */
            }

//            userMutualsCount = sortByValue(userMutualsCount);
//            List<String> listy = new ArrayList<>(userMutualsCount.keySet());
//            HashMap<String, Integer> tempy = new HashMap<String, Integer>();
//            tempy.put(listy.get(0), userMutualsCount.get(listy.get(0)));
//            tempy.put(listy.get(1), userMutualsCount.get(listy.get(1)));
//            tempy.put(listy.get(2), userMutualsCount.get(listy.get(2)));
            //add cases where there are less than 3 users in set related users that the user doesn't follow
            //pass forward mutual count for the "followed by x others line."

            // end of finding related users
            FollowOutputData followOutputData = new FollowOutputData.FollowOutputBuilder(newFollowerCount, true)
                    .build();
            //System.out.println(tempy);
            follower.addFollowing(userBeingFollowedName);
            userBeingFollowed.addFollowers(followerName);
            //userDataAccessObject.updateFollowing(follower, userBeingFollowedName, follow);
            //userDataAccessObject.updateFollowers(userBeingFollowed, followerName, follow);
            followPresenter.prepareSuccessView(followOutputData);
        }

        else {
            // unfollow the user
            int newFollowerCount = userBeingFollowed.getFollowers().size() - 1;


            follower.removeFollowing(userBeingFollowedName);
            userBeingFollowed.removeFollowers(followerName);
            // change the entities


            // update the DAO
            // update your following to remove the person
            //userDataAccessObject.updateFollowing(follower, userBeingFollowedName, follow);
            // update the user being followed's followers to remove you
            //userDataAccessObject.updateFollowers(userBeingFollowed, followerName, follow);

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
