package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_user.SearchController;
import interface_adapter.search_user.SearchPresenter;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.search_user.SearchInteractor;
import use_case.search_user.SearchOutputBoundary;
import use_case.search_user.SearchUserDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;
import view.SearchView;

public class SearchFactory {
    private SearchFactory() {}

    public static SearchView create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, ViewUserViewModel viewUserViewModel, SelectorViewModel selectorViewModel, SearchUserDataAccessInterface dataAccessObject) {
        SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, viewUserViewModel, selectorViewModel, dataAccessObject);
        return new SearchView(searchController, searchViewModel);
    }
    public static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, ViewUserViewModel viewUserViewModel, SelectorViewModel selectorViewModel, SearchUserDataAccessInterface dataAccessObject) {
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel);
        SearchInteractor searchInteractor = new SearchInteractor(dataAccessObject, searchOutputBoundary);
        return new SearchController(searchInteractor);
    }
}
