package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchController;
import interface_adapter.search_user.SearchState;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.search_user.SearchPresenter;
import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListPresenter;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import interface_adapter.view_user.ViewUserController;
import interface_adapter.view_user.ViewUserPresenter;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.follow.FollowInteractor;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.search_user.SearchInteractor;
import use_case.search_user.SearchOutputBoundary;
import use_case.search_user.SearchUserDataAccessInterface;

import use_case.view_user.ViewUserDataAccessInterface;
import use_case.view_user.ViewUserInteractor;
import use_case.view_user.ViewUserOutputBoundary;
import view.SearchView;


public class SearchFactory {

    private SearchFactory() {
    }

    public static SearchView create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, ViewUserViewModel viewUserViewModel, FollowViewModel followViewModel, SearchUserDataAccessInterface userDataAccessObject, ViewUserDataAccessInterface viewUserDAO) {

        SearchController SearchController = createSearchUseCase(viewManagerModel, searchViewModel, viewUserViewModel, userDataAccessObject);
        ViewUserController viewUserController = createViewUserUseCase(viewManagerModel, viewUserViewModel, followViewModel, viewUserDAO);
        //SearchViewModel.setState(new SearchState());
        return new SearchView(SearchController, searchViewModel, viewUserController, viewUserViewModel);
    }
    
    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, ViewUserViewModel viewUserViewModel, SearchUserDataAccessInterface userDataAccessObject) {
        SearchOutputBoundary SearchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel, viewUserViewModel);
        SearchInteractor SearchInteractor = new SearchInteractor(userDataAccessObject, SearchOutputBoundary);
        return new SearchController(SearchInteractor);
    }

    private static ViewUserController createViewUserUseCase(ViewManagerModel viewManagerModel, ViewUserViewModel viewUserViewModel, FollowViewModel followViewModel, ViewUserDataAccessInterface userDataAccessObject) {
        ViewUserOutputBoundary viewUserOutputBoundary = new ViewUserPresenter(viewManagerModel, viewUserViewModel, followViewModel);
        ViewUserInteractor viewUserInteractor = new ViewUserInteractor(userDataAccessObject, viewUserOutputBoundary);
        return new ViewUserController(viewUserInteractor);
    }
}