package factory;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.random_tierlist.RandomTierListController;
import interface_adapter.random_tierlist.RandomTierListPresenter;
import interface_adapter.random_tierlist.RandomTierListState;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListViewModel;
import use_case.generate.GenerateTierListDataAccessInterface;
import use_case.generate.random_tierlist.RandomTierListDataAccessInterface;
import use_case.generate.random_tierlist.RandomTierListInteractor;
import use_case.generate.random_tierlist.RandomTierListOutputBoundary;
import view.RandomTierListView;

public class RandomTierListFactory {
    private RandomTierListFactory() {}
    public static RandomTierListView create(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, GenerateTierListDataAccessInterface fileUserDataAccessObject, RandomTierListDataAccessInterface chatGPTDataAccessObject, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel) {
        RandomTierListController randomTierListController = createRandomUseCase(viewManagerModel, randomTierListViewModel, fileUserDataAccessObject, chatGPTDataAccessObject, tierListViewModel, selectorViewModel);
        randomTierListViewModel.setState(new RandomTierListState(new User("Yael"), "Random"));
        return new RandomTierListView(randomTierListController, randomTierListViewModel);
    }
    private static RandomTierListController createRandomUseCase(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, GenerateTierListDataAccessInterface dataAccessObject, RandomTierListDataAccessInterface chatGPTDataAccessObject, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel) {
        RandomTierListOutputBoundary randomTierListOutputBoundary = new RandomTierListPresenter(viewManagerModel, randomTierListViewModel, tierListViewModel, selectorViewModel);
        RandomTierListInteractor randomTierListInteractor = new RandomTierListInteractor(chatGPTDataAccessObject, dataAccessObject, randomTierListOutputBoundary);
        return new RandomTierListController(randomTierListInteractor);

    }
}
