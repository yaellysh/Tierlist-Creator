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
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
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
        followTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        followTitleLabel.setFont(FollowViewModel.TITLE_FONT);
        followTitleLabel.setAlignmentX(BoxLayout.PAGE_AXIS);

        followTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        this.add(followTitleLabel);
        this.add(new JSeparator());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        JLabel userInfo = new JLabel("Username: " + followViewModel.getState().getUserBeingFollowed().getUsername());

//        userInfo.setAlignmentX(BoxLayout.PAGE_AXIS);
        userInfo.setFont(FollowViewModel.USER_INFO_FONT);

        JLabel followerInfo = new JLabel("Followers: " + followerCount.getText());

//        followerInfo.setAlignmentX(BoxLayout.PAGE_AXIS);
        followerInfo.setFont(FollowViewModel.USER_INFO_FONT);
        followerInfo.setAlignmentX(BoxLayout.PAGE_AXIS);

        JLabel followingInfo = new JLabel("Following: " + followingCount.getText());

//        followingInfo.setAlignmentX(BoxLayout.PAGE_AXIS);
        followingInfo.setFont(FollowViewModel.USER_INFO_FONT);
        followingInfo.setAlignmentX(BoxLayout.PAGE_AXIS);

        infoPanel.add(userInfo);
        infoPanel.add(followingInfo);
        infoPanel.add(followerInfo);

        this.add(infoPanel);

        if (followViewModel.getState().getIsFollowing()) {
            follow = new JButton(followViewModel.FOLLOWING_BUTTON_LABEL);

        } else {
            follow = new JButton(followViewModel.FOLLOW_BUTTON_LABEL);
        }

        JPanel followPanel = new JPanel();
        follow.setOpaque(true);
        follow.setPreferredSize(new Dimension(250, 50));
        follow.setAlignmentX(BoxLayout.PAGE_AXIS);
        followPanel.add(follow);
        this.add(followPanel);
        followPanel.setBorder(new EmptyBorder(20, 10, 10, 10));

        this.add(new JSeparator());

        JLabel tierlistTitle = new JLabel("Tierlists");
        tierlistTitle.setFont(FollowViewModel.TITLE_FONT);
        this.add(tierlistTitle);

        JPanel tierlistPanel = new JPanel();
        tierlistPanel.setLayout(new BoxLayout(tierlistPanel, BoxLayout.PAGE_AXIS));
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

        follow.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(follow)) {
                            FollowState currentState = followViewModel.getState();
                            followController.execute(currentState.getFollower().getUsername(), currentState.getUserBeingFollowed().getUsername(), currentState.getIsFollowing());

                        }
                    }
                });

//        revalidate();
        this.add(new JSeparator());

    }


}

