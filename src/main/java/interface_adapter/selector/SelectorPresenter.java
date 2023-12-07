package interface_adapter.selector;

import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListState;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.random_tierlist.RandomTierListState;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.search_user.SearchState;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.view_existing.ViewExistingState;
import interface_adapter.view_existing.ViewExistingViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.selector.SelectorOutputBoundary;
import use_case.selector.SelectorOutputData;

public class SelectorPresenter implements SelectorOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RandomTierListViewModel randomTierListViewModel;
    private final CustomTierListViewModel customTierListViewModel;
    private final ViewExistingViewModel viewExistingViewModel;
    private final SearchViewModel searchViewModel;
    private final MenuViewModel menuViewModel;
    public SelectorPresenter(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, CustomTierListViewModel customTierListViewModel, ViewExistingViewModel viewExistingViewModel, MenuViewModel menuViewModel, SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.randomTierListViewModel = randomTierListViewModel;
        this.customTierListViewModel = customTierListViewModel;
        this.viewExistingViewModel = viewExistingViewModel;
        this.menuViewModel = menuViewModel;
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView(SelectorOutputData data) {
        switch (data.getLabel()) {
            case "Log Out" -> {
                viewManagerModel.setActiveView(menuViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
            case "Search Users" -> {
                SearchState searchState = searchViewModel.getState();
                searchState.setSearch(data.getUser().getUsername());
                searchViewModel.firePropertyChanged();
                viewManagerModel.setActiveView(searchViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
            case "Random" -> {
                RandomTierListState randomTierListState = randomTierListViewModel.getState();
                randomTierListState.setUser(data.getUser());
                randomTierListViewModel.setState(randomTierListState);
                randomTierListViewModel.firePropertyChanged();
                viewManagerModel.setActiveView(randomTierListViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
            case "Custom" -> {
                CustomTierListState customTierListState = customTierListViewModel.getState();
                customTierListState.setUser(data.getUser());
                customTierListViewModel.setState(customTierListState);
                customTierListViewModel.firePropertyChanged();
                viewManagerModel.setActiveView(customTierListViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
            case "View Existing" -> {
                ViewExistingState viewExistingState = viewExistingViewModel.getState();
                viewExistingState.setUser(data.getUser());
                viewExistingState.setTitle(data.getLabel());
                viewExistingViewModel.setState(viewExistingState);
                viewExistingViewModel.firePropertyChanged();
                viewManagerModel.setActiveView(viewExistingViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        }
    }
}
