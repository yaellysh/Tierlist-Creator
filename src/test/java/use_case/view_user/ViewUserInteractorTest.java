package use_case.view_user;

import data_access.InMemoryUserDataAccessObject;
import entity.TierList;
import entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewUserInteractorTest {

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
    public void viewUserTest() {
        // ensure we can view tim
        ViewUserOutputBoundary presenter = new ViewUserOutputBoundary() {
            @Override
            public void prepareSuccessView(ViewUserOutputData output) {
                int followers = output.getNumFollowers();
                int following = output.getNumFollowing();

                String username = output.getUsername();
                List<String> tierLists = output.getTierLists();

                int expectedFollowers = 3;
                int expectedFollowing = 3;

                assertEquals(expectedFollowers, followers);
                assertEquals(expectedFollowing, following);
                assertEquals(username, tim.getUsername());

            }
        };

        ViewUserInputData input = new ViewUserInputData(tim.getUsername());
        ViewUserInputBoundary interactor = new ViewUserInteractor(userRepository, presenter);
        interactor.execute(input);

    }

}