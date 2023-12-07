package interface_adapter.follow;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FollowViewModel extends ViewModel {

    public final String FOLLOW_BUTTON_LABEL = "Follow";
    public static final String TITLE_LABEL = "User Profile";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 25);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final Font USER_INFO_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final String INSTRUCTIONS = "Enter below the name of an existing user to view their tierlist and follow them.";
    public final String FOLLOWING_LABEL = "<html><b>Following: </b></html>";
    public final String FOLLOWERS_LABEL = "<html><b>Followers: </b></html>";
    public final String FOLLOWING_BUTTON_LABEL = "Following";


    private FollowState state = new FollowState();

    public FollowViewModel(String name) {
        super(name);
    }

    public void setState(FollowState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public FollowState getState() {
        return state;
    }
}