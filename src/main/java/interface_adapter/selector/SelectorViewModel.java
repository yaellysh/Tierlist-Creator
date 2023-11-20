package interface_adapter.selector;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SelectorViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Tier List Selector View";
    public static final String INSTRUCTIONS = "";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 25);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15);
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public SelectorState state;

    public SelectorViewModel(String name) {
        super(name);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SelectorState getState() {
        return this.state;
    }

    public void setState(SelectorState state) {
        this.state = state;
    }
}
