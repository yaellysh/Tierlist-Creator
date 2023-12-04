package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Search User";
    private final SearchViewModel searchViewModel;

    private final JTextField usernameInputField = new JTextField();
    final JButton search;

    private final SearchController searchController;

    public SearchView(SearchController searchController, SearchViewModel searchViewModel) {
        this.searchController = searchController;
        this.searchViewModel = searchViewModel;

        JPanel buttons = new JPanel();

        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        this.add(buttons);

        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel(searchViewModel.SEARCH_BOX_LABEL), usernameInputField);

        search.addActionListener(                
        new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(search)) {
                    SearchState currentState = searchViewModel.getState();
                    searchController.execute(currentState.getSearch());
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
        SearchState state = (SearchState) evt.getNewValue();
    }

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
        });
}
