package interface_adapter.random_tierlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import use_case.generate.random_tierlist.RandomTierListOutputBoundary;
import use_case.generate.random_tierlist.RandomTierListOutputData;

public class RandomTierListPresenter implements RandomTierListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RandomTierListViewModel randomTierListViewModel;
    private final TierListViewModel tierListViewModel;
    private final SelectorViewModel selectorViewModel;
    public RandomTierListPresenter(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.randomTierListViewModel = randomTierListViewModel;
        this.tierListViewModel = tierListViewModel;
        this.selectorViewModel = selectorViewModel;
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
    public void prepareFailView(String error) {
        RandomTierListState randomTierListState = randomTierListViewModel.getState();
        randomTierListState.setError(error);

        randomTierListViewModel.firePropertyChanged();
    }

    @Override
    public void prepareBackView() {
        RandomTierListState state = randomTierListViewModel.getState();
        state.setError(null);
        state.setPrompt(null);
        randomTierListViewModel.setState(state);

        viewManagerModel.setActiveView(selectorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
