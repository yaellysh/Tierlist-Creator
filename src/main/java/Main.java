import factory.FollowFactory;
import factory.SearchFactory;
import factory.TierListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
<<<<<<< HEAD
import interface_adapter.search_user.SearchViewModel;
=======
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.signup.SignupViewModel;
>>>>>>> 039b7d6bc381297089f4e8d9e38f12016f21c55b
import interface_adapter.tierlist.TierListViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.follow.FollowInputBoundary;
import view.FollowView;
import view.SearchView;
import view.TierListView;
import view.ViewManager;

import javax.swing.*;

import data_access.FileUserDataAccessObject;

import java.awt.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame();

        application.setSize(700, 650);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
 
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

<<<<<<< HEAD
        FollowViewModel followViewModel = new FollowViewModel("View User");
        ViewUserViewModel viewUserViewModel = new ViewUserViewModel("View User");
        SearchViewModel searchViewModel = new SearchViewModel("Search User");
=======
        TierListViewModel tierListViewModel = new TierListViewModel("tier list");
        SelectorViewModel selectorViewModel = new SelectorViewModel("selector");
        RandomTierListViewModel randomTierListViewModel = new RandomTierListViewModel("random");
        CustomTierListViewModel customTierListViewModel = new CustomTierListViewModel("custom");
        ViewExistingViewModel viewExistingViewModel = new ViewExistingViewModel("view existing");
        FollowViewModel followViewModel = new FollowViewModel("follow User");
        ViewUserViewModel viewUserViewModel = new ViewUserViewModel("view User");
        MenuViewModel menuViewModel = new MenuViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/main/resources/users.json");
        ChatGPTDataAccessObject chatGPTDataAccessObject  = new ChatGPTDataAccessObject();

        RandomTierListView randomTierListView = RandomTierListFactory.create(viewManagerModel, randomTierListViewModel, userDataAccessObject, chatGPTDataAccessObject, tierListViewModel, selectorViewModel);

        views.add(randomTierListView, randomTierListView.viewName);

//        SearchView searchView = SearchFactory.



        TierListView tierListView = TierListFactory.create(viewManagerModel, tierListViewModel, userDataAccessObject, selectorViewModel);
      
        MenuView menuView = MenuFactory.create(viewManagerModel, menuViewModel, loginViewModel, signupViewModel);
        views.add(menuView, menuView.viewName);

        SelectorView selectorView = SelectorFactory.create(viewManagerModel, selectorViewModel, randomTierListViewModel, customTierListViewModel, userDataAccessObject, viewExistingViewModel, menuViewModel);

        views.add(selectorView, selectorView.viewName);

        SignupView signupView = SignupFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginFactory.create(viewManagerModel, loginViewModel, userDataAccessObject, selectorViewModel);
        views.add(loginView, loginView.viewName);

        ViewExistingView viewExistingView = ViewExistingFactory.create(viewManagerModel, viewExistingViewModel, tierListViewModel, selectorViewModel, userDataAccessObject);
        views.add(viewExistingView, viewExistingView.viewName);

        CustomTierListView customTierListView = CustomTierListFactory.create(viewManagerModel, customTierListViewModel, tierListViewModel, tierListView, selectorViewModel, userDataAccessObject);

        views.add(customTierListView, customTierListView.viewName);
        views.add(tierListView, tierListView.viewName);
>>>>>>> 039b7d6bc381297089f4e8d9e38f12016f21c55b

//        FollowState testing = new FollowState("terryfufu", "lt_rui", false);
//        followViewModel.setState(testing);

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject();

        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, viewUserViewModel, userDataAccessObject, userDataAccessObject);
        views.add(followView, followView.viewName);

<<<<<<< HEAD
        SearchView searchView = SearchFactory.create(viewManagerModel, searchViewModel, viewUserViewModel, followViewModel, userDataAccessObject, userDataAccessObject);
        views.add(searchView, searchView.viewName);

        //application.add(followView);
        //System.out.println(followView.viewName);

        //viewManagerModel.setActiveView(followView.viewName);
        viewManagerModel.setActiveView(searchView.viewName);
        viewManagerModel.firePropertyChanged();

        System.out.println(followViewModel.getViewName());
        System.out.println(searchViewModel.getViewName());

        cardLayout.show(views, "Search User");

        application.setVisible(true);
        /* 
        JFrame window = new JFrame();
        window.setSize(700, 650);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        // added title with padding
        JLabel usernameLabel = new JLabel("username");  
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setVerticalAlignment(JLabel.TOP);
        window.add(usernameLabel);

        JLabel mlabel1 = new JLabel("Mutual 1");  
        window.add(mlabel1, BorderLayout.WEST);

        JLabel mlabel2 = new JLabel("Mutual 2");  
        window.add(mlabel2, BorderLayout.CENTER);

        JLabel mlabel3 = new JLabel("Mutual 3");  
        window.add(mlabel3, BorderLayout.EAST);

        JPanel panel = new JPanel();
        window.add(panel, BorderLayout.SOUTH);
        JButton followButton = new JButton("Follow");
        followButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        followButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.add(followButton);
        panel.setVisible(true);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        


        window.setLocationRelativeTo(null);

        window.setVisible(true);
        */
=======
        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setVisible(true);

>>>>>>> 039b7d6bc381297089f4e8d9e38f12016f21c55b
    }
}