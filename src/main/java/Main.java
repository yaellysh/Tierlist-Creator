import data_access.ChatGPTDataAccessObject;
import data_access.FileUserDataAccessObject;
import factory.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import factory.*;
import factory.CustomTierListFactory;
import factory.FollowFactory;
import factory.LoginFactory;
import factory.MenuFactory;
import factory.RandomTierListFactory;
import factory.SearchFactory;
import factory.SelectorFactory;
import factory.SignupFactory;
import factory.TierListFactory;
import factory.ViewExistingFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.tierlist.TierListViewModel;
import interface_adapter.view_existing.ViewExistingViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.search_user.SearchUserDataAccessInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        JFrame application = new JFrame("Tierlist Maker");
        application.setResizable(false);
        application.setSize(800, 700);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
      
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        TierListViewModel tierListViewModel = new TierListViewModel("tier list");
        SelectorViewModel selectorViewModel = new SelectorViewModel("selector");
        RandomTierListViewModel randomTierListViewModel = new RandomTierListViewModel("random");
        CustomTierListViewModel customTierListViewModel = new CustomTierListViewModel("custom");
        ViewExistingViewModel viewExistingViewModel = new ViewExistingViewModel("view existing");
        FollowViewModel followViewModel = new FollowViewModel("follow User");
        ViewUserViewModel viewUserViewModel = new ViewUserViewModel("vsiew User");
        MenuViewModel menuViewModel = new MenuViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        SearchViewModel searchViewModel = new SearchViewModel("Search User");

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/main/resources/users.json");
        ChatGPTDataAccessObject chatGPTDataAccessObject  = new ChatGPTDataAccessObject();

        RandomTierListView randomTierListView = RandomTierListFactory.create(viewManagerModel, randomTierListViewModel, userDataAccessObject, chatGPTDataAccessObject, tierListViewModel, selectorViewModel);

        views.add(randomTierListView, randomTierListView.viewName);

        SearchView searchView = SearchFactory.create(viewManagerModel, searchViewModel, viewUserViewModel, followViewModel, null, suserDataAccessObject);
        views.add(searchView, searchView.viewName);


        TierListView tierListView = TierListFactory.create(viewManagerModel, tierListViewModel, userDataAccessObject, selectorViewModel);
      
        MenuView menuView = MenuFactory.create(viewManagerModel, menuViewModel, loginViewModel, signupViewModel);
        views.add(menuView, menuView.viewName);

        SelectorView selectorView = SelectorFactory.create(viewManagerModel, selectorViewModel, randomTierListViewModel, customTierListViewModel, userDataAccessObject, viewExistingViewModel, menuViewModel, searchViewModel);

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

//        FollowState testing = new FollowState("terryfufu", "lt_rui", false);
//        followViewModel.setState(testing);

        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, viewUserViewModel, userDataAccessObject, userDataAccessObject);
        views.add(followView, followView.viewName);

        viewManagerModel.setActiveView(menuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setVisible(true);

    }
}