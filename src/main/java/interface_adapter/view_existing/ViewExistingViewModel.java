package interface_adapter.view_existing;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewExistingViewModel extends ViewModel {
    public static final String TITLE_LABEL = "View Existing Tierlists";
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final String INSTRUCTIONS = "From the drop down box below select from a list of you previously created tierlists. <br> Once you select submit you will be able to view and edit their tierlist.";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 30);
    public static final String SUBMIT_BUTTON = "Submit";
    public static final String BACK_BUTTON = "Back";
    public ViewExistingState state = new ViewExistingState();

    public ViewExistingViewModel(String name) {super(name);}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public ViewExistingState getState() {
        return state;
    }

    public void setState(ViewExistingState state) {
        this.state = state;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
