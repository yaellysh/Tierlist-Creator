package interface_adapter.tierlist;

import interface_adapter.ViewManagerModel;
import use_case.tierlist.TierListOutputBoundary;
import use_case.tierlist.TierListOutputData;

public class TierListPresenter implements TierListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final TierListViewModel tierListViewModel;

    public TierListPresenter(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.tierListViewModel = tierListViewModel;
    }

    @Override
    public void prepareSuccessView(TierListOutputData data) {

        TierListState tierListState = tierListViewModel.getState();
        tierListState.setTierList(data.getTierList());
        this.tierListViewModel.setState(tierListState);
        tierListViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(tierListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
