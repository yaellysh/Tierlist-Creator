package factory;

import data_access.FileUserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListController;
import interface_adapter.custom_tierlist.CustomTierListPresenter;
import interface_adapter.custom_tierlist.CustomTierListState;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListViewModel;
import use_case.generate.custom_tierlist.CustomTierListInteractor;
import use_case.generate.custom_tierlist.CustomTierListOutputBoundary;
import view.CustomTierListView;
import view.TierListView;

public class CustomTierListFactory {
    private CustomTierListFactory() {}
    public static CustomTierListView create(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel, TierListViewModel tierListViewModel, TierListView tierListView, SelectorViewModel selectorViewModel){
        CustomTierListController customTierListController = createCustomUseCase(viewManagerModel, customTierListViewModel, tierListViewModel, selectorViewModel);
        customTierListViewModel.setState(new CustomTierListState(new User("Yael")));
        return new CustomTierListView(customTierListController, customTierListViewModel, tierListView);
    }

    private static CustomTierListController createCustomUseCase(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel){
        CustomTierListOutputBoundary customTierListOutputBoundary = new CustomTierListPresenter(viewManagerModel, customTierListViewModel, tierListViewModel, selectorViewModel);
        CustomTierListInteractor customTierListInteractor = new CustomTierListInteractor(customTierListOutputBoundary, new FileUserDataAccessObject("src/main/resources/users.json"));
        return new CustomTierListController(customTierListInteractor);
    }
}
