package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.tierlist.TierListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class FollowView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "follow user";
    private final FollowViewModel followViewModel;
    private final FollowController followController;
    //    private boolean mutualsDisplayed = false;
    ArrayList<JButton> mutualButtonList = new ArrayList<JButton>();
    //    JPanel mutuals = new JPanel();
    BoxLayout boxLayoutM;
    //    private final ViewUserViewModel viewUserViewModel;
//
    private JLabel followingLabel;
//    private JButton mutual1;
//    private JButton mutual2;
//    private JButton mutual3;
    private JLabel followersLabel;
    private ViewManagerModel viewManagerModel;
    private JButton follow;
    private JLabel username = new JLabel("");
    private JLabel followerCount = new JLabel();
    private JLabel followingCount = new JLabel();
    private boolean tierlistsDisplayed = false;
    private boolean usernameDisplayed = false;
    private TierListViewModel tierListViewModel;


    public FollowView(FollowController followController, FollowViewModel followViewModel, TierListViewModel tierListViewModel, ViewManagerModel viewManagerModel) {
        this.followController = followController;
        this.followViewModel = followViewModel;
        this.viewManagerModel = viewManagerModel;
        this.add(username);
        this.add(followingCount);
        this.tierListViewModel = tierListViewModel;
        this.add(followerCount);
        followViewModel.addPropertyChangeListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        removeAll();

        JPanel mainPanel = new JPanel();

        JLabel followerCount = new JLabel(Integer.toString(followViewModel
                .getState()
                .getFollower()
                .getFollowers()
                .size()));
//        mainPanel.add(followerCount);
        JLabel followingCount = new JLabel(Integer.toString(followViewModel.
                getState()
                .getFollower()
                .getFollowing()
                .size()));
//        mainPanel.add(followingCount);

        
        JLabel followTitleLabel = new JLabel(FollowViewModel.TITLE_LABEL);
        followTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        followTitleLabel.setFont(FollowViewModel.TITLE_FONT);
        this.add(followTitleLabel);
        followTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(new JSeparator());

        JPanel userInfoPanel = new JPanel();
        JLabel userInfo = new JLabel("<html><b>Username:</b> " + followViewModel.getState().getUserBeingFollowed().getUsername() + "</html>");
        userInfo.setFont(FollowViewModel.USER_INFO_FONT);
        userInfoPanel.add(userInfo);
        this.add(userInfoPanel);

        JPanel followerFollowingPanel = new JPanel();
//        followerFollowingPanel.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel followerPanel = new JPanel();
        this.followersLabel = new JLabel(followViewModel.FOLLOWERS_LABEL);
        followerPanel.add(followersLabel);
        followerPanel.add(followerCount);

        JPanel followingPanel = new JPanel();
        this.followingLabel = new JLabel(followViewModel.FOLLOWING_LABEL);
        followingPanel.add(followingLabel);
        followingPanel.add(followingCount);

        followerFollowingPanel.add(followingPanel);
        followerFollowingPanel.add(followerPanel);

        this.add(followerFollowingPanel);

        followViewModel.addPropertyChangeListener(this);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

        this.setLayout(boxLayout);

        this.add(mainPanel);



        if (followViewModel.getState().getIsFollowing()) {
            follow = new JButton(followViewModel.FOLLOWING_BUTTON_LABEL);
        } else {
            follow = new JButton(followViewModel.FOLLOW_BUTTON_LABEL);
        }

        mainPanel.add(follow);

        JPanel tierlistPanel = new JPanel();
        List<JButton> tierListButtons = new ArrayList<>();
        List<String> tierLists = followViewModel.getState().getTierLists();
        for (String tierList : tierLists) {
            ButtonPanel buttonPanel = new ButtonPanel(tierList);
            tierlistPanel.add(buttonPanel);
            tierListButtons.add(buttonPanel.getButton());
        }
        this.add(tierlistPanel);
        for (JButton button : tierListButtons) {
            button.addActionListener(e -> {
                if (e.getSource() == button) {
                    followController.execute(
                            followViewModel.getState().getFollower().getUsername(),
                            followViewModel.getState().getUserBeingFollowed().getUsername(),
                            true);
                }
            });
        }

        follow.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(follow)) {
                            FollowState currentState = followViewModel.getState();
                            followController.execute(currentState.getFollower().getUsername(), currentState.getUserBeingFollowed().getUsername(), currentState.getIsFollowing());

                        }
                    }
                });

        revalidate();

    }


}

