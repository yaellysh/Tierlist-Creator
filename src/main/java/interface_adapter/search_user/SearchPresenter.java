package interface_adapter.search_user;

import interface_adapter.ViewManagerModel;
import use_case.search_user.SearchOutputBoundary;
import use_case.search_user.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(SearchOutputData searchOutputData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepareSuccessView'");
    }


    @Override
    public void prepareFailView(String error) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepareFailView'");
    }
}
