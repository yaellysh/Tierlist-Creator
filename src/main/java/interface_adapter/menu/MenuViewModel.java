package interface_adapter.menu;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MenuViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Sign Up and Log In";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 30);
    public static final String SIGNUP_INSTRUCTIONS = "If you don't have an existing account, please select the 'Sign Up'" +
            " button and create a username and password.";
    public static final String LOGIN_INSTRUCTIONS = "If you have already signed up, select the 'Login' button and enter your existing username and password. <br> From here you can view and edit your existing tierlists or create new ones.";
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15);
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
