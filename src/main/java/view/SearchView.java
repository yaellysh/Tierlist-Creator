package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchController;
import interface_adapter.search_user.SearchState;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_user.ViewUserController;
import interface_adapter.view_user.ViewUserViewModel;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Search User";
    private final SearchViewModel searchViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    final JButton search;
    private JButton userfound = new JButton();
    private JLabel userNotFoundText = new JLabel();



    private final SearchController searchController;
    private ViewUserController viewUserController;
    private ViewUserViewModel viewUserViewModel;

    public SearchView(SearchController searchController, SearchViewModel searchViewModel, ViewUserController viewUserController, ViewUserViewModel viewUserViewModel) {
        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        this.viewUserController = viewUserController;
        this.viewUserViewModel = viewUserViewModel;
        searchViewModel.addPropertyChangeListener(this);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        JPanel buttons = new JPanel();
        this.add(usernameInputField);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(searchViewModel.SEARCH_BOX_LABEL), usernameInputField);
        this.add(usernameInfo);

        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        this.add(buttons);
        this.add(userNotFoundText);
        

        //LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel(searchViewModel.SEARCH_BOX_LABEL), usernameInputField);

        search.addActionListener(                
        new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(search)) {
                    
                    SearchState currentState = searchViewModel.getState();
                    if (currentState.getSearch() == "") {
                        userNotFoundText.setText("Please enter a Username");
                        
                    }
                    else {
                        searchController.execute(currentState.getSearch());
                    }
                    
                }
            }
        });



        usernameInputField.addKeyListener(
        new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchState currentState = searchViewModel.getState();
                String text = usernameInputField.getText() + e.getKeyChar();
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
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState)evt.getNewValue();
        System.out.println(state.getSearchError());
        if (state.getSuccess()) {
            userfound.setText(state.getSearch());
            this.add(userfound);
            if (userNotFoundText != null) {
                userNotFoundText.setVisible(false);
            }
            userfound.addActionListener(                
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(userfound)) {
                       viewUserController.execute(userfound.getText());

                    }
                }
            });
        }
        else {
            System.out.println("ok");
            userNotFoundText.setText(state.getSearchError());
            this.add(userNotFoundText);
            if (userfound != null) {
                userfound.setVisible(false);
            }
            
        }
    }
}
