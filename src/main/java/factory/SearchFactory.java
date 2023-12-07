package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchController;
import interface_adapter.search_user.SearchPresenter;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.view_user.ViewUserController;
import interface_adapter.view_user.ViewUserPresenter;
import interface_adapter.view_user.ViewUserViewModel;
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

        SearchController SearchController = createSearchUseCase(viewManagerModel, searchViewModel, followViewModel, userDataAccessObject);
        ViewUserController viewUserController = createViewUserUseCase(viewManagerModel, viewUserViewModel, followViewModel, viewUserDAO);
        return new SearchView(SearchController, searchViewModel, viewUserController, viewUserViewModel);
    }
    
    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, FollowViewModel followViewModel, SearchUserDataAccessInterface userDataAccessObject) {
        SearchOutputBoundary SearchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel, followViewModel);
        SearchInteractor SearchInteractor = new SearchInteractor(userDataAccessObject, SearchOutputBoundary);
        return new SearchController(SearchInteractor);
    }

    private static ViewUserController createViewUserUseCase(ViewManagerModel viewManagerModel, ViewUserViewModel viewUserViewModel, FollowViewModel followViewModel, ViewUserDataAccessInterface userDataAccessObject) {
        ViewUserOutputBoundary viewUserOutputBoundary = new ViewUserPresenter(viewManagerModel, viewUserViewModel, followViewModel);
        ViewUserInteractor viewUserInteractor = new ViewUserInteractor(userDataAccessObject, viewUserOutputBoundary);
        return new ViewUserController(viewUserInteractor);
    }
}