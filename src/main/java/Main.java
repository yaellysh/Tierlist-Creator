
import factory.FollowFactory;
import factory.TierListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.tierlist.TierListViewModel;
import use_case.follow.FollowInputBoundary;
import view.FollowView;
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

        FollowViewModel followViewModel = new FollowViewModel("View User");

        HashMap<String, Integer> testmap = new HashMap<String, Integer>();
        FollowState testing = new FollowState("terryfufu", "lt_rui", false);
        followViewModel.setState(testing);

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject();

        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, userDataAccessObject);
        views.add(followView, followView.viewName);

        //application.add(followView);
        //System.out.println(followView.viewName);

        viewManagerModel.setActiveView(followView.viewName);
        viewManagerModel.firePropertyChanged();

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