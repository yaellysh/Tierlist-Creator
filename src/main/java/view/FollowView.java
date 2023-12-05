package view;

import javax.swing.*;

import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.view_user.ViewUserController;
import interface_adapter.view_user.ViewUserViewModel;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.jar.JarEntry;

public class FollowView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "View User";
    private final FollowViewModel followViewModel;

    final JButton follow;
    JPanel mutuals = new JPanel();
    BoxLayout boxLayoutM;

    private final FollowController followController;

    public FollowView(FollowController followController, FollowViewModel followViewModel, ViewUserController viewUserController, ViewUserViewModel viewUserViewModel) {
        this.followController = followController;
        this.followViewModel = followViewModel;
        followViewModel.addPropertyChangeListener(this);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        boxLayoutM = new BoxLayout(mutuals, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        JPanel panely = new JPanel();
        this.add(panely);
        
        

        JLabel username = new JLabel(followViewModel.getState().getUserBeingFollowed()); //this should be gotten from viewUserViewModel
        panely.add(username);
        JLabel followerCount = new JLabel(Integer.toString(viewUserViewModel.getState().getNumFollowing()));
        panely.add(followerCount);
        JLabel followingCount = new JLabel(Integer.toString(viewUserViewModel.getState().getNumFollowers()));
        panely.add(followingCount);

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
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        FollowState state = (FollowState) evt.getNewValue();
        if (state.getIsFollowing() == true){
            follow.setText(followViewModel.FOLLOWING_BUTTON_LABEL);
            mutuals = new JPanel();
            this.add(mutuals);
            for (Map.Entry<String, Integer> entry : state.getRelatedUsers().entrySet()) {
                
                JPanel tempy = new JPanel();
                mutuals.add(tempy);
                JButton username = new JButton(entry.getKey());
                tempy.add(username);
                JLabel count = new JLabel("You have " + Integer.toString(entry.getValue()) + " mutuals with this user.");
                tempy.add(count);
            }
        }
        else {
            follow.setText(followViewModel.FOLLOW_BUTTON_LABEL);
            this.remove(mutuals);
        }
        
    }

}
