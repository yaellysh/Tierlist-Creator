package view;

import javax.swing.*;

import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FollowView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "View User";
    private final FollowViewModel followViewModel;

    final JButton follow;

    private final FollowController followController;

    public FollowView(FollowController followController, FollowViewModel followViewModel) {
        this.followController = followController;
        this.followViewModel = followViewModel;
        followViewModel.addPropertyChangeListener(this);
        JLabel test = new JLabel(followViewModel.getState().getUserBeingFollowed());
        this.add(test);

        JPanel buttons = new JPanel();
        if (followViewModel.getState().getIsFollowing()) {
            System.out.println("workoisjsjdao");
            follow = new JButton(followViewModel.FOLLOWING_BUTTON_LABEL);
        }
        else {
            follow = new JButton(followViewModel.FOLLOW_BUTTON_LABEL);
        }
        
        buttons.add(follow);
        this.add(buttons);

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
            JFrame application = new JFrame();
            application.setSize(700, 650);
            application.setVisible(true);
        }
        else {
            System.out.println("fhfj");
            follow.setText(followViewModel.FOLLOW_BUTTON_LABEL);
        }
        
    }

}
