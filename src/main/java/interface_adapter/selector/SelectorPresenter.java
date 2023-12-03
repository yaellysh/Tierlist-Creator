package interface_adapter.selector;

import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListState;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.random_tierlist.RandomTierListState;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import use_case.selector.SelectorOutputBoundary;
import use_case.selector.SelectorOutputData;

public class SelectorPresenter implements SelectorOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SelectorViewModel selectorViewModel;
    private final RandomTierListViewModel randomTierListViewModel;
    private final CustomTierListViewModel customTierListViewModel;
    public SelectorPresenter(ViewManagerModel viewManagerModel, SelectorViewModel selectorViewModel, RandomTierListViewModel randomTierListViewModel, CustomTierListViewModel customTierListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.selectorViewModel = selectorViewModel;
        this.randomTierListViewModel = randomTierListViewModel;
        this.customTierListViewModel = customTierListViewModel;
    }

    @Override
    public void prepareSuccessView(SelectorOutputData data) {
        if (data.getLabel().equals("RANDOM")) {
            RandomTierListState randomTierListState = randomTierListViewModel.getState();
            randomTierListState.setUser(data.getUser());
            randomTierListViewModel.setState(randomTierListState);
            randomTierListViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(randomTierListViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else if (data.getLabel().equals("CUSTOM")) {
            CustomTierListState customTierListState = customTierListViewModel.getState();
            customTierListState.setUser(data.getUser());
            customTierListViewModel.setState(customTierListState);
            customTierListViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(customTierListViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }
}
