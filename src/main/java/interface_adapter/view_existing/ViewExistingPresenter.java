package interface_adapter.view_existing;

import interface_adapter.ViewManagerModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import use_case.view_existing.ViewExistingOutputBoundary;
import use_case.view_existing.ViewExistingOutputData;

public class ViewExistingPresenter implements ViewExistingOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final TierListViewModel tierListViewModel;
    private final SelectorViewModel selectorViewModel;
    public ViewExistingPresenter(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.tierListViewModel = tierListViewModel;
        this.selectorViewModel = selectorViewModel;
    }
    @Override
    public void prepareSuccessView(ViewExistingOutputData data) {
        TierListState tierListState = tierListViewModel.getState();
        tierListState.setTierList(data.getTierList());
        tierListState.setUser(data.getUser());
        tierListViewModel.setState(tierListState);
        tierListViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(tierListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView() {

    }
    @Override
    public void prepareBackView() {
        viewManagerModel.setActiveView(selectorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
