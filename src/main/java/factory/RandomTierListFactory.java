package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.random_tierlist.RandomTierListController;
import interface_adapter.random_tierlist.RandomTierListPresenter;
import interface_adapter.random_tierlist.RandomTierListState;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListViewModel;
import use_case.generate.random_tierlist.RandomTierListDataAccessInterface;
import use_case.generate.random_tierlist.RandomTierListInteractor;
import use_case.generate.random_tierlist.RandomTierListOutputBoundary;
import use_case.tierlist.TierListDataAccessInterface;
import view.RandomTierListView;

public class RandomTierListFactory {
    private RandomTierListFactory() {}
    public static RandomTierListView create(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, TierListDataAccessInterface fileUserDataAccessObject, RandomTierListDataAccessInterface chatGPTDataAccessObject, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel) {
        RandomTierListController randomTierListController = createRandomUseCase(viewManagerModel, randomTierListViewModel, fileUserDataAccessObject, chatGPTDataAccessObject, tierListViewModel, selectorViewModel);
        randomTierListViewModel.setState(new RandomTierListState(fileUserDataAccessObject.getUser("Yael"), null)); // TODO: unhardcode this
        return new RandomTierListView(randomTierListController, randomTierListViewModel);
    }
    private static RandomTierListController createRandomUseCase(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, TierListDataAccessInterface dataAccessObject, RandomTierListDataAccessInterface chatGPTDataAccessObject, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel) {
        RandomTierListOutputBoundary randomTierListOutputBoundary = new RandomTierListPresenter(viewManagerModel, randomTierListViewModel, tierListViewModel, selectorViewModel);
        RandomTierListInteractor randomTierListInteractor = new RandomTierListInteractor(chatGPTDataAccessObject, dataAccessObject, randomTierListOutputBoundary);
        return new RandomTierListController(randomTierListInteractor);

    }
}
