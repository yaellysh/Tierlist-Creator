package use_case.follow;

import data_access.FileUserDataAccessObject;
import entity.User;
import factory.FollowFactory;
import factory.SearchFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowPresenter;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.tierlist.TierListViewModel;
import org.junit.Test;
import view.FollowView;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class FollowPresenterTest {

    @Test
    public void FollowPresenterTest() {
        JFrame application = new JFrame();

        application.setSize(700, 650);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        FollowViewModel followViewModel = new FollowViewModel("View User");
        SearchViewModel searchViewModel = new SearchViewModel("Search User");
        TierListViewModel tierListViewModel = new TierListViewModel("tier list");

        User user = new User("terryfufu");
        User user2 = new User("lt_rui");

        FollowState testing = new FollowState(user, user2, false, new ArrayList<>());
        followViewModel.setState(testing);

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/test/resources/users.json");


        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, userDataAccessObject, tierListViewModel);
        views.add(followView, followView.viewName);
        followViewModel.getState().setIsFollowing(true);
        followViewModel.getState().getIsFollowing();
        followViewModel.getViewName();
        followViewModel.getClass();
        followViewModel.getState().getIsFollowing();
        followViewModel.getState().getFollower();
        followViewModel.getState().getUserBeingFollowed();

        SearchView searchView = SearchFactory.create(viewManagerModel, searchViewModel, followViewModel, userDataAccessObject);
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

        FollowOutputData output = new FollowOutputData.FollowOutputBuilder(52, true).build();
        FollowPresenter presenter = new FollowPresenter(viewManagerModel, followViewModel, tierListViewModel);
        presenter.prepareSuccessView(output);


    }


}
