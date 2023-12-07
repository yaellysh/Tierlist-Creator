package interface_adapter.search_user;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {

    public final String SEARCH_BOX_LABEL = "Enter a username:";
    public final String SEARCH_BUTTON_LABEL = "Search";
    public static final String TITLE_LABEL = "Search Users";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 25);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final String INSTRUCTIONS = "Enter below the name of an existing user to view their tierlist and follow them.";


    private SearchState state = new SearchState();

    public SearchViewModel(String name) {
        super(name);
    }

    public void setState(SearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchState getState() {
        return state;
    }
}