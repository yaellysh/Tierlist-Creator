package interface_adapter.signup;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Username:";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15);

    public static final String PASSWORD_LABEL = "Password:";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again:";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return state;
    }
}
