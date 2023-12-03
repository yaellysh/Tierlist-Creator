package factory;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_tierlist.CustomTierListViewModel;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.selector.SelectorController;
import interface_adapter.selector.SelectorPresenter;
import interface_adapter.selector.SelectorState;
import interface_adapter.selector.SelectorViewModel;
import use_case.selector.SelectorInteractor;
import use_case.selector.SelectorOutputBoundary;
import view.SelectorView;

public class SelectorFactory {
    private SelectorFactory() {}
    public static SelectorView create(ViewManagerModel viewManagerModel, SelectorViewModel selectorViewModel, RandomTierListViewModel randomTierListViewModel, CustomTierListViewModel customTierListViewModel) {
        SelectorController selectorController = createSelectorUseCase(viewManagerModel, selectorViewModel, randomTierListViewModel, customTierListViewModel);
        selectorViewModel.setState(new SelectorState(new User("Yael"))); // TODO: currently hardcoded, will change when login is implemented
        return new SelectorView(selectorController, selectorViewModel);
    }

    private static SelectorController createSelectorUseCase(ViewManagerModel viewManagerModel, SelectorViewModel selectorViewModel, RandomTierListViewModel randomTierListViewModel, CustomTierListViewModel customTierListViewModel) {
        SelectorOutputBoundary selectorOutputBoundary = new SelectorPresenter(viewManagerModel, selectorViewModel, randomTierListViewModel, customTierListViewModel);
        SelectorInteractor selectorInteractor = new SelectorInteractor(selectorOutputBoundary);
        return new SelectorController(selectorInteractor);
    }
}
