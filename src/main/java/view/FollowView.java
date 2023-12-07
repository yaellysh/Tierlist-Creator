package view;

import javax.swing.*;

import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.view_user.ViewUserController;
import interface_adapter.view_user.ViewUserState;
import interface_adapter.view_user.ViewUserViewModel;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

public class FollowView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "follow User";
    private final FollowViewModel followViewModel;
    private final ViewUserViewModel viewUserViewModel;

    private final JLabel followingLabel;
    private final JLabel followersLabel;

    private JButton follow;
    private JButton mutual1;
    private JButton mutual2;
    private JButton mutual3;

    private JLabel username = new JLabel("");

    private JLabel followerCount  = new JLabel();
    private JLabel followingCount= new JLabel();
    private boolean tierlistsDisplayed = false;
    private boolean usernameDisplayed = false;


    private boolean mutualsDisplayed = false;
    ArrayList<JButton> mutualButtonList = new ArrayList<JButton>();
    JPanel panely = new JPanel();
    
    JPanel mutuals = new JPanel();
    BoxLayout boxLayoutM;

    private final FollowController followController;
    private final ViewUserController viewUserController;

    public FollowView(FollowController followController, FollowViewModel followViewModel, ViewUserController viewUserController, ViewUserViewModel viewUserViewModel) {
        this.followController = followController;
        this.followViewModel = followViewModel;
        this.viewUserViewModel = viewUserViewModel;
        this.viewUserController = viewUserController;
        this.add(username);
        this.add(followingCount);
        this.add(followerCount);

        JPanel followerPanel = new JPanel();
        followersLabel =  new JLabel(viewUserViewModel.FOLLOWERS_LABEL);
        followerPanel.add(followersLabel);
        followerPanel.add(followerCount);


        JPanel followingPanel = new JPanel();
        followingLabel = new JLabel(viewUserViewModel.FOLLOWING_LABEL);
        followingPanel.add(followingLabel);
        followingPanel.add(followingCount);

        this.add(followingPanel);
        this.add(followerPanel);

        followViewModel.addPropertyChangeListener(this);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        boxLayoutM = new BoxLayout(mutuals, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        this.add(panely);
        
        
        System.out.println(viewUserViewModel.getState().getUsername());


//        JLabel followerCount = new JLabel(Integer.toString(viewUserViewModel.getState().getNumFollowing()));
//        panely.add(followerCount);
//        JLabel followingCount = new JLabel(Integer.toString(viewUserViewModel.getState().getNumFollowers()));
//        panely.add(followingCount);

        /*
        for (String tl : viewUserViewModel.getState().getTierLists()) {
            JLabel temp = new JLabel(tl);
            this.add(temp);
        }
        */

        if (followViewModel.getState().getIsFollowing()) {
            follow = new JButton(followViewModel.FOLLOWING_BUTTON_LABEL);
        }
        else {
            follow = new JButton(followViewModel.FOLLOW_BUTTON_LABEL);
        }

        mutual1 = new JButton();
        mutual2 = new JButton();
        mutual3 = new JButton();
        mutualButtonList.add(mutual1);
        mutualButtonList.add(mutual2);
        mutualButtonList.add(mutual3);
        
    
        
        panely.add(follow);

        follow.addActionListener(                
        new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(follow)) {
                    FollowState currentState = followViewModel.getState();
<<<<<<< HEAD
                    System.out.println(viewUserViewModel.getState().getNumFollowers() + "before execute call");
                    System.out.println("working");
=======
>>>>>>> 039b7d6bc381297089f4e8d9e38f12016f21c55b
                    if (!currentState.getIsFollowing()) {
                        followController.execute(currentState.getFollower(), currentState.getUserBeingFollowed(), false);
                    }
                    else {
                        followController.execute(currentState.getFollower(), currentState.getUserBeingFollowed(), true);
                    }

                    
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getSource());

        System.out.println("propertychanged");
        ViewUserState viewUserState = viewUserViewModel.getState();
        System.out.println(viewUserState.getUsername());
        if (viewUserState.getUsername() != "" || (evt.getSource().equals(mutual1) || evt.getSource().equals(mutual2) || evt.getSource().equals(mutual3))) {
            usernameDisplayed = true;
            username.setText(viewUserViewModel.getState().getUsername());
            panely.add(username);
            followingCount.setText(Integer.toString(viewUserViewModel.getState().getNumFollowing()));
            followerCount.setText(Integer.toString(viewUserViewModel.getState().getNumFollowers()));

        }

        System.out.println(viewUserState.getTierLists());

        if (!viewUserState.getTierLists().isEmpty()&&!tierlistsDisplayed) {
            JLabel tlTitle = new JLabel(viewUserViewModel.TIERLIST_LABEL);
            this.add(tlTitle);
            tierlistsDisplayed = true;
            for (String tl: viewUserState.getTierLists()) {
                System.out.println(tl);
                //make a button that has text as name of tierlist, and add an actionlister for every button
                JButton tierListButton = new JButton();
                tierListButton.setText(tl);
                //such that when the button is clicked, it goes to view the tl.
                tierListButton.addActionListener(                
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(tierListButton)) {
                            //Change to tierlist view
                        
                        }
                    }
                });
                this.add(tierListButton);
            }
        }


        FollowState followState = (FollowState) evt.getNewValue();
        if (followState.getIsFollowing() && !mutualsDisplayed){
            mutualsDisplayed = true;
            followerCount.setText(Integer.toString(viewUserViewModel.getState().getNumFollowers()));
            System.out.println(Integer.toString(viewUserViewModel.getState().getNumFollowers()));
            follow.setText(followViewModel.FOLLOWING_BUTTON_LABEL);
            mutuals = new JPanel();
            this.add(mutuals);
            List<Integer> counts = new ArrayList<Integer>(followState.getRelatedUsers().values());
            List<String> usernames = new ArrayList<String>(followState.getRelatedUsers().keySet());
            for (int i = 0; i < 3; i++) {
                
                JPanel tempy = new JPanel();
                mutuals.add(tempy);
                mutualButtonList.get(i).setText(usernames.get(i));
                tempy.add(mutualButtonList.get(i));
                JLabel count = new JLabel("You have " + Integer.toString(counts.get(i)) + " mutuals with this user.");
                tempy.add(count);
                if (i == 0) {
                    mutual1.addActionListener(                
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(mutual1)) {
                                usernameDisplayed = false;
                                viewUserController.execute(mutual1.getText());
                            }
                        }
                    });
                }
                if (i == 1) {
                    mutual2.addActionListener(                
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(mutual2)) {
                                usernameDisplayed = false;
                                viewUserController.execute(mutual1.getText());
                            }
                        }
                    });
                }
                if (i == 2) {
                    mutual3.addActionListener(                
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(mutual3)) {
                                usernameDisplayed = false;
                                viewUserController.execute(mutual1.getText());
                            }
                        }
                    });
                }
            }
        }
        else if (mutualsDisplayed ) {
            follow.setText(followViewModel.FOLLOW_BUTTON_LABEL);
            followerCount.setText(Integer.toString(viewUserViewModel.getState().getNumFollowers()));
            mutualsDisplayed = false;
        
            this.remove(mutuals);
        }

        

        
    }

}
