package factory;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.random_tierlist.RandomTierListController;
import interface_adapter.random_tierlist.RandomTierListPresenter;
import interface_adapter.random_tierlist.RandomTierListState;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import use_case.generate.random_tierlist.RandomTierListInteractor;
import use_case.generate.random_tierlist.RandomTierListOutputBoundary;
import view.RandomTierListView;

public class RandomTierListFactory {
    private RandomTierListFactory() {}
    public static RandomTierListView create(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel) {
        RandomTierListController randomTierListController = createRandomUseCase(viewManagerModel, randomTierListViewModel);
        randomTierListViewModel.setState(new RandomTierListState(new User("Yael"), "Random"));
        return new RandomTierListView(randomTierListController, randomTierListViewModel);
    }
    private static RandomTierListController createRandomUseCase(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel) {
        RandomTierListOutputBoundary randomTierListOutputBoundary = new RandomTierListPresenter(viewManagerModel, randomTierListViewModel);
        RandomTierListInteractor randomTierListInteractor = new RandomTierListInteractor(randomTierListOutputBoundary);
        return new RandomTierListController(randomTierListInteractor);

    }
}
