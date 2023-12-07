package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchController;
import interface_adapter.search_user.SearchPresenter;
import interface_adapter.search_user.SearchViewModel;
import use_case.search_user.SearchInteractor;
import use_case.search_user.SearchOutputBoundary;
import use_case.search_user.SearchUserDataAccessInterface;
import view.SearchView;

public class SearchFactory {

    private SearchFactory() {
    }

    public static SearchView create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, FollowViewModel followViewModel, SearchUserDataAccessInterface userDataAccessObject) {

        SearchController SearchController = createSearchUseCase(viewManagerModel, searchViewModel, followViewModel, userDataAccessObject);
        return new SearchView(SearchController, searchViewModel);
    }
    
    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, FollowViewModel followViewModel, SearchUserDataAccessInterface userDataAccessObject) {
        SearchOutputBoundary SearchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel, followViewModel);
        SearchInteractor SearchInteractor = new SearchInteractor(userDataAccessObject, SearchOutputBoundary);
        return new SearchController(SearchInteractor);
    }
}