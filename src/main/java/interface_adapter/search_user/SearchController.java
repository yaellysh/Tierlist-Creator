package interface_adapter.search_user;

import use_case.search_user.SearchInputBoundary;
import use_case.search_user.SearchInputData;

public class SearchController {
    final SearchInputBoundary searchUseCaseInteractor;

    public SearchController(SearchInputBoundary searchInputBoundary) {
        searchUseCaseInteractor = searchInputBoundary;
    }

    public void execute(String search) {
        SearchInputData inputData = new SearchInputData(search);
        searchUseCaseInteractor.execute(inputData);
    }
}
