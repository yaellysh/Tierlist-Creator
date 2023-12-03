package interface_adapter.random_tierlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import use_case.generate.random_tierlist.RandomTierListOutputBoundary;
import use_case.generate.random_tierlist.RandomTierListOutputData;

public class RandomTierListPresenter implements RandomTierListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RandomTierListViewModel randomTierListViewModel;
    private final TierListViewModel tierListViewModel;
    public RandomTierListPresenter(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, TierListViewModel tierListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.randomTierListViewModel = randomTierListViewModel;
        this.tierListViewModel = tierListViewModel;
    }
    @Override
    public void prepareSuccessView(RandomTierListOutputData data) {
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
}
