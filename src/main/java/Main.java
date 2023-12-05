import data_access.ChatGPTDataAccessObject;
import data_access.FileUserDataAccessObject;
import factory.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.tierlist.TierListViewModel;
import interface_adapter.view_existing.ViewExistingViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

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
        FollowViewModel followViewModel = new FollowViewModel("View User");
        ViewUserViewModel viewUserViewModel = new ViewUserViewModel("View User");
        MenuViewModel menuViewModel = new MenuViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/main/resources/users.json");
        ChatGPTDataAccessObject chatGPTDataAccessObject  = new ChatGPTDataAccessObject();

        RandomTierListView randomTierListView = RandomTierListFactory.create(viewManagerModel, randomTierListViewModel, userDataAccessObject, chatGPTDataAccessObject, tierListViewModel, selectorViewModel);

        views.add(randomTierListView, randomTierListView.viewName);

        SelectorView selectorView = SelectorFactory.create(viewManagerModel, selectorViewModel, randomTierListViewModel, customTierListViewModel, userDataAccessObject, viewExistingViewModel);

        views.add(selectorView, selectorView.viewName);

        TierListView tierListView = TierListFactory.create(viewManagerModel, tierListViewModel, userDataAccessObject, selectorViewModel);
      
        MenuView menuView = MenuFactory.create(viewManagerModel, menuViewModel, loginViewModel, signupViewModel);
        views.add(menuView, menuView.viewName);

        SignupView signupView = SignupFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = LoggedInFactory.create(viewManagerModel, loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);
      
        ViewExistingView viewExistingView = ViewExistingFactory.create(viewManagerModel, viewExistingViewModel, tierListViewModel, selectorViewModel, userDataAccessObject);
        views.add(viewExistingView, viewExistingView.viewName);

        CustomTierListView customTierListView = CustomTierListFactory.create(viewManagerModel, customTierListViewModel, tierListViewModel, tierListView, selectorViewModel, userDataAccessObject);

        views.add(customTierListView, customTierListView.viewName);

        FollowState testing = new FollowState("terryfufu", "lt_rui", false);
        followViewModel.setState(testing);

        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, viewUserViewModel, userDataAccessObject, userDataAccessObject);
        views.add(followView, followView.viewName);

//        viewManagerModel.setActiveView(tierListView.viewName);
//        viewManagerModel.setActiveView(randomTierListView.viewName);
        viewManagerModel.setActiveView(menuView.viewName);
//        viewManagerModel.setActiveView(customTierListView.viewName);
//        viewManagerModel.setActiveView(tierListView.viewName);
        viewManagerModel.firePropertyChanged();

        //application.add(followView);
        //System.out.println(followView.viewName);
      
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
        application.setVisible(true);
    }
}