package interface_adapter.custom_tierlist;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CustomTierListViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Custom Tier List";
    public static final String INSTRUCTIONS = "Please enter the appropriate inputs in each of the fields below please, leaving none empty. When you are satisfied, press submit to generate your tierlist.";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 30);
    public static final Font BUTTON_FONT = new Font("Arial", Font.ITALIC, 25);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final String SUBMIT_BUTTON = "Submit";
    public CustomTierListState state;

    public CustomTierListState getState() {
        return state;
    }

    public void setState(CustomTierListState state) {
        this.state = state;
    }

    public CustomTierListViewModel(String name) {
        super(name);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
