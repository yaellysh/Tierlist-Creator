package interface_adapter.search_user;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.Icon;

public class SearchViewModel extends ViewModel {

    public final String SEARCH_BOX_LABEL = "Enter a username:";
    public final String SEARCH_BUTTON_LABEL = "Search";

    private SearchState state = new SearchState();

    public SearchViewModel() {
        super("search user");
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