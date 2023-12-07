package interface_adapter.tierlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.selector.SelectorViewModel;
import use_case.tierlist.TierListOutputBoundary;
import use_case.tierlist.TierListOutputData;

public class TierListPresenter implements TierListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final TierListViewModel tierListViewModel;
    private final SelectorViewModel selectorViewModel;

    public TierListPresenter(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.tierListViewModel = tierListViewModel;
        this.selectorViewModel = selectorViewModel;
    }

    @Override
    public void prepareSuccessView(TierListOutputData data) {
        TierListState tierListState = tierListViewModel.getState();
        tierListState.setTierList(data.getTierList());
        this.tierListViewModel.setState(tierListState);
        tierListViewModel.firePropertyChanged();

        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        TierListState tierListState = tierListViewModel.getState();
        tierListState.setError(error);
        this.tierListViewModel.firePropertyChanged();
    }

    @Override
    public void prepareBackView(){
        viewManagerModel.setActiveView(selectorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
