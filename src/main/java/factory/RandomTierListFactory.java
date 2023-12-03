package factory;

import data_access.ChatGPTDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.random_tierlist.RandomTierListController;
import interface_adapter.random_tierlist.RandomTierListPresenter;
import interface_adapter.random_tierlist.RandomTierListState;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.tierlist.TierListViewModel;
import use_case.generate.random_tierlist.RandomTierListInteractor;
import use_case.generate.random_tierlist.RandomTierListOutputBoundary;
import view.RandomTierListView;

public class RandomTierListFactory {
    private RandomTierListFactory() {}
    public static RandomTierListView create(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, FileUserDataAccessObject fileUserDataAccessObject, ChatGPTDataAccessObject chatGPTDataAccessObject, TierListViewModel tierListViewModel) {
        RandomTierListController randomTierListController = createRandomUseCase(viewManagerModel, randomTierListViewModel, fileUserDataAccessObject, chatGPTDataAccessObject, tierListViewModel);
        randomTierListViewModel.setState(new RandomTierListState(new User("Yael"), "Random"));
        return new RandomTierListView(randomTierListController, randomTierListViewModel);
    }
    private static RandomTierListController createRandomUseCase(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, FileUserDataAccessObject dataAccessObject, ChatGPTDataAccessObject chatGPTDataAccessObject, TierListViewModel tierListViewModel) {
        RandomTierListOutputBoundary randomTierListOutputBoundary = new RandomTierListPresenter(viewManagerModel, randomTierListViewModel, tierListViewModel);
        RandomTierListInteractor randomTierListInteractor = new RandomTierListInteractor(chatGPTDataAccessObject, dataAccessObject, randomTierListOutputBoundary);
        return new RandomTierListController(randomTierListInteractor);

    }
}
