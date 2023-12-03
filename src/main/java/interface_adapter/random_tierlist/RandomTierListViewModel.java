package interface_adapter.random_tierlist;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RandomTierListViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Random Tier List";
    public static final String INSTRUCTIONS = "Enter below the prompt you wish to use to generate a new tierlist. <br> Please note that the tierlist may take a while to load once you select submit.";
    public static final String EG = "Please ensure that your input is a logical topic for a tier list. Examples of" +
            " inputs are: <br> &nbsp&nbsp&nbsp&nbsp&nbsp - Ghibli movies <br> &nbsp&nbsp&nbsp&nbsp&nbsp - Pokemon" +
            " <br> &nbsp&nbsp&nbsp&nbsp&nbsp - Restaurant chains <br> &nbsp&nbsp&nbsp&nbsp&nbsp - Magicians";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 30);
    public static final Font TEXT_FONT_LARGE = new Font("Arial", Font.PLAIN, 15);
    public static final Font BUTTON_FONT = new Font("Arial", Font.ITALIC, 25);
    public static final Font TEXT_FONT_SMALL = new Font("Arial", Font.ITALIC, 13);
    public static final String SUBMIT_BUTTON = "Submit";
    public static final String BACK_BUTTON = "Back";

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
