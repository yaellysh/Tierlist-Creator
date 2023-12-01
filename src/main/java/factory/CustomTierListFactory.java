package factory;

import data_access.FileUserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListController;
import interface_adapter.custom_tierlist.CustomTierListPresenter;
import interface_adapter.custom_tierlist.CustomTierListState;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import use_case.generate.custom_tierlist.CustomTierListInteractor;
import use_case.generate.custom_tierlist.CustomTierListOutputBoundary;
import view.CustomTierListView;

import java.util.ArrayList;

public class CustomTierListFactory {
    private CustomTierListFactory() {}
    public static CustomTierListView create(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel){
        CustomTierListController customTierListController = createCustomUseCase(viewManagerModel, customTierListViewModel);
        customTierListViewModel.setState(new CustomTierListState(new User("Yael"), new ArrayList<>()));
        return new CustomTierListView(customTierListController, customTierListViewModel);
    }

    private static CustomTierListController createCustomUseCase(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel){
        CustomTierListOutputBoundary customTierListOutputBoundary = new CustomTierListPresenter(viewManagerModel, customTierListViewModel);
        CustomTierListInteractor customTierListInteractor = new CustomTierListInteractor(customTierListOutputBoundary, new FileUserDataAccessObject("src/main/resources/users.json"));
        return new CustomTierListController(customTierListInteractor);
    }
}
