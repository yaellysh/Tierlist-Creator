package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.tierlist.TierListViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);


        JLabel followerCount = new JLabel(Integer.toString(followViewModel
                .getState()
                .getFollower()
                .getFollowers()
                .size()));

        JLabel followingCount = new JLabel(Integer.toString(followViewModel.
                getState()
                .getFollower()
                .getFollowing()
                .size()));


        JLabel followTitleLabel = new JLabel(FollowViewModel.TITLE_LABEL);
        followTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        followTitleLabel.setFont(FollowViewModel.TITLE_FONT);
        followTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(followTitleLabel);
        this.add(new JSeparator());


        JPanel infoPanel = new JPanel();
        BoxLayout infoBoxLayout = new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS);

        infoPanel.setLayout(infoBoxLayout);

        JLabel userInfo = new JLabel("Username: " + followViewModel.getState().getUserBeingFollowed().getUsername());
        userInfo.setFont(FollowViewModel.USER_INFO_FONT);
        userInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(userInfo);

        JLabel followingInfo = new JLabel("Following: " + followingCount.getText());
        followingInfo.setFont(FollowViewModel.USER_INFO_FONT);
        followingInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(followingInfo);

        JLabel followersInfo = new JLabel("Followers: " + followerCount.getText());
        followersInfo.setFont(FollowViewModel.USER_INFO_FONT);
        followersInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(followersInfo);

        JLabel tierListTitleLabel = new JLabel("Tierlists");
        tierListTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        tierListTitleLabel.setFont(FollowViewModel.TITLE_FONT);
        tierListTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel followWrapper = new JPanel();
        ButtonPanel follow;
        if (followViewModel.getState().getIsFollowing()) {
            follow = new ButtonPanel(followViewModel.FOLLOWING_BUTTON_LABEL);
        } else {
            follow = new ButtonPanel(followViewModel.FOLLOW_BUTTON_LABEL);
        }
//        followWrapper.setBorder(new EmptyBorder(0, 0, 20, 0));
        followWrapper.add(follow);
        infoPanel.add(followWrapper);
        infoPanel.add(new JSeparator());
        infoPanel.add(tierListTitleLabel);


        JPanel tierlistPanel = new JPanel();
        BoxLayout tierListBoxLayout = new BoxLayout(tierlistPanel, BoxLayout.PAGE_AXIS);
        tierlistPanel.setLayout(tierListBoxLayout);

        List<JButton> tierListButtons = new ArrayList<>();
        List<String> tierLists = followViewModel.getState().getTierLists();
        for (String tierList : tierLists) {
            ButtonPanel buttonPanel = new ButtonPanel(tierList);
            buttonPanel.setMaximumSize(new Dimension(275, 60));
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
                            button.getText());
                }
            });
        }

        follow.getButton().addActionListener(
                evt1 -> {
                    if (evt1.getSource().equals(follow.getButton())) {
                        FollowState currentState = followViewModel.getState();
                        followController.execute(currentState.getFollower().getUsername(), currentState.getUserBeingFollowed().getUsername(), currentState.getIsFollowing());
                    }
                });


        infoPanel.add(tierlistPanel);
        this.add(infoPanel);

        this.add(new JSeparator());

        revalidate();

    }


}

