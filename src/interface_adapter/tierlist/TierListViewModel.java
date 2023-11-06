package interface_adapter.tierlist;

import interface_adapter.ViewModel;
import interface_adapter.tierlist.TierListState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TierListViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Tier List View";
    public static final String PINK_LABEL = "pink";
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
