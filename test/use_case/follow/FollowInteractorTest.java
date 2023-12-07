package use_case.follow;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import factory.UserFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FollowInteractorTest {

    private User userA;
    private User userB;

    private User userC;
    private User userD;
    private User userE;
    private User userF;

    private User tim;

    private User terry;




    private InMemoryUserDataAccessObject userRepository;

    @Before
    public void setUp() {
        userA = new User("User A");
        userB = new User("User B");
        userC = new User("User C");
        userD = new User("User D");
        userE = new User("User E");
        userF = new User("User F");
        tim = new User("lt_rui");
        terry = new User("terryfufu");

        userA.addFollowing("lt_rui");
        userB.addFollowing("lt_rui");
        userC.addFollowing("lt_rui");
        userD.addFollowing("User A");
        userE.addFollowing("User A");
        userF.addFollowing("User A");
        userE.addFollowing("User B");
        userF.addFollowing("User B");
        userF.addFollowing("User C");

        tim.addFollowing("User A");
        tim.addFollowing("User B");
        tim.addFollowing("User C");

        tim.addFollowers("User A");
        tim.addFollowers("User B");
        tim.addFollowers("User C");

        terry.addFollowing("User D");
        terry.addFollowing("User E");
        terry.addFollowing("User F");

        userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(userC);
        userRepository.save(userD);
        userRepository.save(userE);
        userRepository.save(userF);
        userRepository.save(tim);
        userRepository.save(terry);
    }


    @Test
    public void followTest() {

        // FollowUserDataAccessInterface userRepository = new

        //terry follows tim

        FollowOutputBoundary successPresenter = new FollowOutputBoundary() {

            @Override
            public void prepareSuccessView(FollowOutputData output) {

                int expectedNum = 4;

                // make sure user B has user A as a follower

                assertEquals(expectedNum, output.getNewFollowers());

                ArrayList<String> expectedFollower = new ArrayList<>();
                expectedFollower.add("User A");
                expectedFollower.add("User B");
                expectedFollower.add("User C");
                expectedFollower.add(terry.getUsername());
                assertEquals(expectedFollower, tim.getFollowers());

                // make sure user A is following user B

                ArrayList<String> expectedFollowing = new ArrayList<>();
                HashMap<String, Integer> users = output.getRelatedUsers();
                expectedFollowing.add("User D");
                expectedFollowing.add("User E");
                expectedFollowing.add("User F");
                expectedFollowing.add(tim.getUsername());
                assertEquals(expectedFollowing, terry.getFollowing());

                assertTrue(output.getFollow());

            }
        };

        FollowInputData input = new FollowInputData(terry.getUsername(), tim.getUsername(), false);
        FollowInputBoundary interactor = new FollowInteractor(userRepository, successPresenter);
        interactor.execute(input);
    }

}