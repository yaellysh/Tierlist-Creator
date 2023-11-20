package interface_adapter.selector;

import interface_adapter.ViewManagerModel;
import use_case.selector.SelectorOutputBoundary;
import use_case.selector.SelectorOutputData;

public class SelectorPresenter implements SelectorOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SelectorViewModel selectorViewModel;
    public SelectorPresenter(ViewManagerModel viewManagerModel, SelectorViewModel selectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.selectorViewModel = selectorViewModel;
    }
    public void prepareSuccessView(SelectorOutputData data) {
        SelectorState selectorState = selectorViewModel.getState();
        viewManagerModel.firePropertyChanged();
    }
}
