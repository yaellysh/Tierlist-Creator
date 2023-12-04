package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListViewModel;
import interface_adapter.view_existing.ViewExistingController;
import interface_adapter.view_existing.ViewExistingPresenter;
import interface_adapter.view_existing.ViewExistingState;
import interface_adapter.view_existing.ViewExistingViewModel;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.view_existing.ViewExistingInteractor;
import use_case.view_existing.ViewExistingOutputBoundary;
import view.ViewExistingView;

public class ViewExistingFactory {
    private ViewExistingFactory() {
    }

    public static ViewExistingView create(ViewManagerModel viewManagerModel, ViewExistingViewModel viewExistingViewModel, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel, TierListDataAccessInterface dataAccessObject) {
        ViewExistingController viewExistingController = createExistingUseCase(viewManagerModel, tierListViewModel,selectorViewModel);
        viewExistingViewModel.setState(new ViewExistingState(dataAccessObject.getUser("Yael"), null));
        return new ViewExistingView(viewExistingController, viewExistingViewModel);
    }
    private static ViewExistingController createExistingUseCase(ViewManagerModel viewManagerModel , TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel ) {
        ViewExistingOutputBoundary viewExistingOutputBoundary = new ViewExistingPresenter(viewManagerModel, tierListViewModel, selectorViewModel);
        ViewExistingInteractor viewExistingInteractor = new ViewExistingInteractor(viewExistingOutputBoundary);
        return new ViewExistingController(viewExistingInteractor);

    }
}
