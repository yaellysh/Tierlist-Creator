package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchController;
import interface_adapter.search_user.SearchState;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.tierlist.TierListViewModel;
import interface_adapter.view_user.ViewUserController;
import interface_adapter.view_user.ViewUserViewModel;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";
    private final SearchViewModel searchViewModel;

//    private JTextField usernameInputField = new JTextField(15);
    final JButton search;
    private JButton userfound = new JButton();
    private JLabel userNotFoundText = new JLabel();

    private final SearchController searchController;
    private ViewUserController viewUserController;


    public SearchView(SearchController searchController, SearchViewModel searchViewModel, ViewUserController viewUserController, ViewUserViewModel viewUserViewModel) {
        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        this.viewUserController = viewUserController;
        searchViewModel.addPropertyChangeListener(this);

        searchViewModel.addPropertyChangeListener(this);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        JPanel buttons = new JPanel();

        JLabel searchTitleLabel = new JLabel(SearchViewModel.TITLE_LABEL);
        searchTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        searchTitleLabel.setFont(SearchViewModel.TITLE_FONT);
        searchTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(searchTitleLabel);

        JLabel instructions = new JLabel();
        instructions.setText("<html>" + SearchViewModel.INSTRUCTIONS + "</html>");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        this.add(instructions);
        this.add(new JSeparator());
        this.add(Box.createRigidArea(new Dimension(10, -350)));

        InputPanel usernameInfo = new InputPanel(searchViewModel.SEARCH_BOX_LABEL);

        this.add(usernameInfo);



        this.search = new JButton(CustomTierListViewModel.SUBMIT_BUTTON);

        search.setOpaque(true);
        search.setPreferredSize(new Dimension(250, 50));
        search.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(search);
        buttons.setBorder(new EmptyBorder(50, 10, 10,10));
        this.add(buttons);
        this.add(userNotFoundText);

        search.addActionListener(
                evt -> {
                    if (evt.getSource().equals(search)) {
                        SearchState currentState = searchViewModel.getState();
                        if (Objects.equals(currentState.getSearch(), "")) {
                            userNotFoundText.setText("Please enter a Username");

                        }
                        else {
                            searchController.execute(currentState.getSearch());
                        }

                    }
                });

        usernameInfo.getTextField().addKeyListener(
        new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchState currentState = searchViewModel.getState();
                String text = usernameInfo.getTextField().getText() + e.getKeyChar();
                currentState.setSearch(text);
                searchViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        }
    );
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState)evt.getNewValue();

        if (state.getSuccess()) {

//            viewUserController.execute(state.getSearch());
//
//            if (userNotFoundText != null) {
//                userNotFoundText.setVisible(false);
//            }
//
//            userfound.addActionListener(
//            new ActionListener() {
//                public void actionPerformed(ActionEvent evt) {
//                    if (evt.getSource().equals(userfound)) {
//                       viewUserController.execute(userfound.getText());
//
//                    }
//                }
//            });
        }
        else {
            userNotFoundText.setText(state.getSearchError());
            this.add(userNotFoundText);
            if (userfound != null) {
                userfound.setVisible(false);
            }

        }
    }
}
