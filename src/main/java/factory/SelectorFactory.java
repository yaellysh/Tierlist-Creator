package factory;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.selector.SelectorController;
import interface_adapter.selector.SelectorPresenter;
import interface_adapter.selector.SelectorState;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListState;
import use_case.selector.SelectorDataAccessInterface;
import use_case.selector.SelectorInteractor;
import use_case.selector.SelectorOutputBoundary;
import view.SelectorView;
import view.TierListView;

public class SelectorFactory {
    private SelectorFactory() {}
    public static SelectorView create(ViewManagerModel viewManagerModel, SelectorViewModel selectorViewModel, SelectorDataAccessInterface userDataAccessObject) {
        SelectorController selectorController = createSelectorUseCase(viewManagerModel, selectorViewModel, userDataAccessObject);
        selectorViewModel.setState(new SelectorState(new User("Yael"))); // TODO: currently hardcoded, will change when login is implemented
        return new SelectorView(selectorController, selectorViewModel);
    }

    private static SelectorController createSelectorUseCase(ViewManagerModel viewManagerModel, SelectorViewModel selectorViewModel, SelectorDataAccessInterface userDataAccessObject) {
        SelectorOutputBoundary selectorOutputBoundary = new SelectorPresenter(viewManagerModel, selectorViewModel);
        SelectorInteractor selectorInteractor = new SelectorInteractor(userDataAccessObject, selectorOutputBoundary);
        return new SelectorController(selectorInteractor);
    }
}
