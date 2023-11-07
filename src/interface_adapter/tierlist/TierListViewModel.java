package interface_adapter.tierlist;

import entity.Tier;
import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TierListViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Tier List View";
    public static final String INSTRUCTIONS = "In the drop down options below please select the tier you wish" +
            " to place each item in. To view the corresponding tier list, press the 'generate' button.";
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 25);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final int NUM_TIERS = Tier.values().length;
    public static final String GENERATE_BUTTON = "Generate";

    private TierListState state = new TierListState();

    public TierListViewModel() {
        super("tier list");
    }

    public void setState(TierListState state) {
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

    public TierListState getState() {
        return state;
    }
}
