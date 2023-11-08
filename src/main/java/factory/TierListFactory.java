package factory;

import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListPresenter;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.tierlist.TierListInteractor;
import use_case.tierlist.TierListOutputBoundary;
import view.TierListView;

import java.util.ArrayList;

public class TierListFactory {

    private TierListFactory() {
    }

    public static TierListView create(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel, TierListDataAccessInterface userDataAccessObject) {

        TierListController tierListController = createTierListUseCase(viewManagerModel, tierListViewModel, userDataAccessObject);
        tierListViewModel.setState(new TierListState(userDataAccessObject.getUser("Yael"), "Test"));
        return new TierListView(tierListController, tierListViewModel);
    }
    private static TierListController createTierListUseCase(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel, TierListDataAccessInterface userDataAccessObject) {
        TierListOutputBoundary tierListOutputBoundary = new TierListPresenter(viewManagerModel, tierListViewModel);
        TierListInteractor tierInteractor = new TierListInteractor(userDataAccessObject, tierListOutputBoundary);
        return new TierListController(tierInteractor);
    }
}
