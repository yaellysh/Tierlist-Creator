package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListPresenter;
import interface_adapter.tierlist.TierListViewModel;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.tierlist.TierListInteractor;
import use_case.tierlist.TierListOutputBoundary;
import view.TierListView;

public class TierListFactory {

    private TierListFactory() {
    }

    public static TierListView create(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel, TierListDataAccessInterface userDataAccessObject, SelectorViewModel selectorViewModel) {

        TierListController tierListController = createTierListUseCase(viewManagerModel, tierListViewModel, userDataAccessObject, selectorViewModel);
        return new TierListView(tierListController, tierListViewModel);
    }
    private static TierListController createTierListUseCase(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel, TierListDataAccessInterface userDataAccessObject, SelectorViewModel selectorViewModel) {
        TierListOutputBoundary tierListOutputBoundary = new TierListPresenter(viewManagerModel, tierListViewModel, selectorViewModel);
        TierListInteractor tierInteractor = new TierListInteractor(userDataAccessObject, tierListOutputBoundary);
        return new TierListController(tierInteractor);
    }

}
