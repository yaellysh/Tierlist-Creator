package interface_adapter.random_tierlist;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RandomTierListViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Random Tier List";
    public static final String INSTRUCTIONS = "Enter below the prompt you wish to use to generate a new tierlist. <br><br>  Examples of valid inputs are: <br> &nbsp &nbsp - ";
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
