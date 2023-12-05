package interface_adapter.menu;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MenuViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Tierlist Maker";

    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";

    private MenuState state = new MenuState();

    public MenuViewModel() {
        super("menu");
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MenuState getState() {
        return state;
    }
}
