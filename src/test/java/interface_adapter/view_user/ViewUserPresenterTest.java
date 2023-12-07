package interface_adapter.view_user;

import data_access.FileUserDataAccessObject;
import factory.FollowFactory;
import factory.SearchFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowPresenter;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchViewModel;
import org.junit.Test;
import use_case.follow.FollowOutputData;
import use_case.view_user.ViewUserOutputData;
import view.FollowView;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ViewUserPresenterTest {

    @Test
    public void viewPresenterTest() {
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
        followViewModel.getState().setIsFollowing(true);
        followViewModel.getState().setRelatedUsers(null);
        followViewModel.getState().getIsFollowing();
        followViewModel.getViewName();
        followViewModel.getClass();
        followViewModel.getState().getIsFollowing();
        followViewModel.getState().getFollower();
        followViewModel.getState().getUserBeingFollowed();

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

        assertSame("follow User", viewManagerModel.getActiveView());

        ViewUserOutputData output = new ViewUserOutputData("hi", new ArrayList<>(), 5, 2);
        ViewUserPresenter presenter = new ViewUserPresenter(viewManagerModel, viewUserViewModel, followViewModel);
        presenter.prepareSuccessView(output);


    }

}