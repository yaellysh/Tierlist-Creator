package interface_adapter.view_user;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewUserViewModel extends ViewModel {
    public final String FOLLOWING_LABEL = "Following";
    public final String FOLLOWERS_LABEL = "Followers";

    public static final String TIERLIST_LABEL = "Tier Lists";

    private ViewUserState state = new ViewUserState();

    public ViewUserViewModel(String name) {
        super(name);
    }

    public void setState(ViewUserState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ViewUserState getState() {
        return state;
    }

}
