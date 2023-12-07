import data_access.FileUserDataAccessObject;
import factory.FollowFactory;
import factory.SearchFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import view.FollowView;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

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

        FollowViewModel followViewModel = new FollowViewModel("View User");
        ViewUserViewModel viewUserViewModel = new ViewUserViewModel("View User");
        SearchViewModel searchViewModel = new SearchViewModel("Search User");

        FollowState testing = new FollowState("terryfufu", "lt_rui", false);


        //ok
        followViewModel.setState(testing);

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject();

        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, viewUserViewModel, userDataAccessObject, userDataAccessObject);
        views.add(followView, followView.viewName);

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
    }
}