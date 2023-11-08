package factory;

import entity.Item;
import entity.Tier;
import entity.TierList;
import interface_adapter.ViewManagerModel;
import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListPresenter;
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
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 1; i < 5; i++) {
            Item item = new Item("Item " + i);
            item.setTier(Tier.S);
            items.add(item);
        }
        for (int i = 5; i < 8; i++) {
            Item item = new Item("Item " + i);
            item.setTier(Tier.A);
            items.add(item);
        }
        for (int i = 8; i < 10; i++) {
            Item item = new Item("Item " + i);
            item.setTier(Tier.C);
            items.add(item);
        }
        TierList tierList = new TierList("Test", items);
        return new TierListView(tierListController, tierListViewModel, tierList);
    }
    private static TierListController createTierListUseCase(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel, TierListDataAccessInterface userDataAccessObject) {
        TierListOutputBoundary tierListOutputBoundary = new TierListPresenter(viewManagerModel, tierListViewModel);
        TierListInteractor tierInteractor = new TierListInteractor(userDataAccessObject, tierListOutputBoundary);
        return new TierListController(tierInteractor);
    }
}
