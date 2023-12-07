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

    public final String viewName = "View User";
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



    final
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
                    System.out.println("working");
                    if (!currentState.getIsFollowing()) {
                        followController.execute(currentState.getFollower(), currentState.getUserBeingFollowed(), false);
                    }
                    else {
                        followController.execute(currentState.getFollower(), currentState.getUserBeingFollowed(), true);
                    }
                    
                }
            }
        });

        mutual1.addActionListener(                
        new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(mutual1)) {
                    System.out.println("User A");
                    ViewUserState currentState = viewUserViewModel.getState();
                    viewUserController.execute(mutual1.getText());
                    System.out.println(viewUserViewModel.getState().getUsername());
                    JLabel newy = new JLabel(viewUserViewModel.getState().getUsername());
                    
                }
            }
        });

        mutual2.addActionListener(                
        new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(mutual2)) {
                    System.out.println("User B");
                }
            }
        });

        mutual3.addActionListener(                
        new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(mutual3)) {
                    System.out.println("User C");
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
        ViewUserState viewUserState = viewUserViewModel.getState();
        System.out.println(viewUserState.getUsername() +"lasjdas");
        if (viewUserState.getUsername() != "" && username.getText().length()==0) {
            username.setText(viewUserViewModel.getState().getUsername());
            panely.add(username);
            followingCount.setText(Integer.toString(viewUserViewModel.getState().getNumFollowing()));
            followerCount.setText(Integer.toString(viewUserViewModel.getState().getNumFollowers()));

        }

        if (!viewUserState.getTierLists().isEmpty()) {
            for (String tl: viewUserState.getTierLists()) {
                //make a button that has text as name of tierlist, and add an actionlister for every button
                JButton tierListButton = new JButton();
                tierListButton.setText(tl);
                //such that when the button is clicked, it goes to view the tl.
            }
        }

        FollowState followState = (FollowState) evt.getNewValue();
        if (followState.getIsFollowing()){
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
                int placeholder = i;
                mutualButtonList.get(i).addActionListener(                
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(mutualButtonList.get(placeholder))) {
                            System.out.println("clicked");
                        
                        }
                    }
                });
            }
        }
        else {
            follow.setText(followViewModel.FOLLOW_BUTTON_LABEL);
            this.remove(mutuals);
        }
        
    }

}
