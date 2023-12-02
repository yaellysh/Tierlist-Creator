package factory;

import data_access.FileUserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListController;
import interface_adapter.custom_tierlist.CustomTierListPresenter;
import interface_adapter.custom_tierlist.CustomTierListState;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.tierlist.TierListViewModel;
import use_case.generate.custom_tierlist.CustomTierListInteractor;
import use_case.generate.custom_tierlist.CustomTierListOutputBoundary;
import view.CustomTierListView;

public class CustomTierListFactory {
    private CustomTierListFactory() {}
    public static CustomTierListView create(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel, TierListViewModel tierListViewModel){
        CustomTierListController customTierListController = createCustomUseCase(viewManagerModel, customTierListViewModel, tierListViewModel);
        customTierListViewModel.setState(new CustomTierListState(new User("Yael")));
        return new CustomTierListView(customTierListController, customTierListViewModel);
    }

    private static CustomTierListController createCustomUseCase(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel, TierListViewModel tierListViewModel){
        CustomTierListOutputBoundary customTierListOutputBoundary = new CustomTierListPresenter(viewManagerModel, customTierListViewModel, tierListViewModel);
        CustomTierListInteractor customTierListInteractor = new CustomTierListInteractor(customTierListOutputBoundary, new FileUserDataAccessObject("src/main/resources/users.json"));
        return new CustomTierListController(customTierListInteractor);
    }
}
