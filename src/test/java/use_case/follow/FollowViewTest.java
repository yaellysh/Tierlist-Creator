package use_case.follow;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;
import factory.FollowFactory;
import factory.SearchFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import org.junit.Before;
import org.junit.Test;
import view.FollowView;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertSame;

public class FollowViewTest {

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
    public void followViewTest() {

        JFrame application = new JFrame();

        application.setSize(700, 650);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
 
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        FollowViewModel followViewModel = new FollowViewModel("View User");
        ViewUserViewModel viewUserViewModel = new ViewUserViewModel("View User");
        SearchViewModel searchViewModel = new SearchViewModel("Search User");

        FollowState testing = new FollowState("terryfufu", "lt_rui", false);
        followViewModel.setState(testing);

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/test/resources/users.json");

        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, viewUserViewModel, userDataAccessObject, userDataAccessObject);
        views.add(followView, followView.viewName);

        SearchView searchView = SearchFactory.create(viewManagerModel, searchViewModel, viewUserViewModel, followViewModel, userDataAccessObject, userDataAccessObject);
        views.add(searchView, searchView.viewName);

        //application.add(followView);
        //System.out.println(followView.viewName);

        //viewManagerModel.setActiveView(followView.viewName);
        viewManagerModel.setActiveView(followView.viewName);
        viewManagerModel.firePropertyChanged();

        System.out.println(followViewModel.getViewName());
        System.out.println(searchViewModel.getViewName());

        cardLayout.show(views, "Search User");

        application.setVisible(true);

        assertSame("View User", viewManagerModel.getActiveView());
    }

}