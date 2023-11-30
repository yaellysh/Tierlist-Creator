package interface_adapter.random_tierlist;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RandomTierListViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Random Tier List";
    public static final String INSTRUCTIONS = "Enter below the prompt you wish to use to generate a new tierlist.";
    public static final String EG = "Examples of inputs are: <br> &nbsp&nbsp&nbsp&nbsp&nbsp - ghibli movies <br> &nbsp&nbsp&nbsp&nbsp&nbsp - pokemon <br> &nbsp&nbsp&nbsp&nbsp&nbsp - restaurant chains";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 30);
    public static final Font TEXT_FONT_LARGE = new Font("Arial", Font.PLAIN, 15);
    public static final Font BUTTON_FONT = new Font("Arial", Font.ITALIC, 25);
    public static final Font TEXT_FONT_SMALL = new Font("Arial", Font.ITALIC, 13);
    public static final String SUBMIT_BUTTON = "Submit";

    public RandomTierListState state;

    public RandomTierListViewModel(String name) {
        super(name);
    }

    public void setState(RandomTierListState state) {
        this.state = state;
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

    public RandomTierListState getState() {
        return state;
    }
}
