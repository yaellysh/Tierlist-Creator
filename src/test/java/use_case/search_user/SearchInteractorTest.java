package use_case.search_user;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchInteractorTest {
    private User userA;

    private InMemoryUserDataAccessObject userRepository;

    @Before
    public void Setup() {
        userA = new User("User A");
        userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(userA);
    }

    @Test
    public void searchTest() {
        // search for "User A"

        // SearchOutputBoundary searchPresenter = new
    }

}