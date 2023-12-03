package factory;

import data_access.ChatGPTDataAccessObject;
import data_access.FileUserDataAccessObject;
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
    public static RandomTierListView create(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, FileUserDataAccessObject fileUserDataAccessObject, ChatGPTDataAccessObject chatGPTDataAccessObject) {
        RandomTierListController randomTierListController = createRandomUseCase(viewManagerModel, randomTierListViewModel, fileUserDataAccessObject, chatGPTDataAccessObject);
        randomTierListViewModel.setState(new RandomTierListState(new User("Yael"), "Random"));
        return new RandomTierListView(randomTierListController, randomTierListViewModel);
    }
    private static RandomTierListController createRandomUseCase(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel, FileUserDataAccessObject dataAccessObject, ChatGPTDataAccessObject chatGPTDataAccessObject) {
        RandomTierListOutputBoundary randomTierListOutputBoundary = new RandomTierListPresenter(viewManagerModel, randomTierListViewModel);
        RandomTierListInteractor randomTierListInteractor = new RandomTierListInteractor(chatGPTDataAccessObject, dataAccessObject, randomTierListOutputBoundary);
        return new RandomTierListController(randomTierListInteractor);

    }
}
