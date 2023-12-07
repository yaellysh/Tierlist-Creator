package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.selector.SelectorController;
import interface_adapter.selector.SelectorPresenter;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.view_existing.ViewExistingViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.selector.SelectorInteractor;
import use_case.selector.SelectorOutputBoundary;
import use_case.tierlist.TierListDataAccessInterface;
import view.SelectorView;

public class SelectorFactory {
    private SelectorFactory() {}
<<<<<<< HEAD
    public static SelectorView create(ViewManagerModel viewManagerModel, SelectorViewModel selectorViewModel, RandomTierListViewModel randomTierListViewModel, CustomTierListViewModel customTierListViewModel, TierListDataAccessInterface dataAccessInterface, ViewExistingViewModel viewExistingViewModel, MenuViewModel menuViewModel, SearchViewModel searchViewmodel) {
        SelectorController selectorController = createSelectorUseCase(viewManagerModel, randomTierListViewModel, customTierListViewModel, viewExistingViewModel, menuViewModel, searchViewmodel);
=======
    public static SelectorView create(ViewManagerModel viewManagerModel, SelectorViewModel selectorViewModel, RandomTierListViewModel randomTierListViewModel, CustomTierListViewModel customTierListViewModel, TierListDataAccessInterface dataAccessInterface, ViewExistingViewModel viewExistingViewModel, MenuViewModel menuViewModel, SearchViewModel searchViewModel) {
        SelectorController selectorController = createSelectorUseCase(viewManagerModel, randomTierListViewModel, customTierListViewModel, viewExistingViewModel, menuViewModel, searchViewModel);
>>>>>>> a45df5bdc92d19958134a3eb1c9eccf6a5b160f3
        return new SelectorView(selectorController, selectorViewModel);
    }

    private static SelectorController createSelectorUseCase(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, CustomTierListViewModel customTierListViewModel, ViewExistingViewModel viewExistingViewModel, MenuViewModel menuViewModel, SearchViewModel searchViewModel) {
        SelectorOutputBoundary selectorOutputBoundary = new SelectorPresenter(viewManagerModel, randomTierListViewModel, customTierListViewModel, viewExistingViewModel, menuViewModel, searchViewModel);
        SelectorInteractor selectorInteractor = new SelectorInteractor(selectorOutputBoundary);
        return new SelectorController(selectorInteractor);
    }
}
