package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListController;
import interface_adapter.custom_tierlist.CustomTierListPresenter;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListViewModel;
import use_case.custom_tierlist.CustomTierListInteractor;
import use_case.custom_tierlist.CustomTierListOutputBoundary;
import use_case.tierlist.TierListDataAccessInterface;
import view.CustomTierListView;
import view.TierListView;

public class CustomTierListFactory {
    private CustomTierListFactory() {}
    public static CustomTierListView create(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel, TierListViewModel tierListViewModel, TierListView tierListView, SelectorViewModel selectorViewModel, TierListDataAccessInterface dataAccessObject){
        CustomTierListController customTierListController = createCustomUseCase(viewManagerModel, customTierListViewModel, tierListViewModel, selectorViewModel, dataAccessObject);
        return new CustomTierListView(customTierListController, customTierListViewModel, tierListView);
    }

    private static CustomTierListController createCustomUseCase(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel, TierListDataAccessInterface dataAccessObject){
        CustomTierListOutputBoundary customTierListOutputBoundary = new CustomTierListPresenter(viewManagerModel, customTierListViewModel, tierListViewModel, selectorViewModel);
        CustomTierListInteractor customTierListInteractor = new CustomTierListInteractor(customTierListOutputBoundary, dataAccessObject);
        return new CustomTierListController(customTierListInteractor);
    }
}
