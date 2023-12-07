package interface_adapter.search_user;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.search_user.SearchOutputBoundary;
import use_case.search_user.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ViewUserViewModel viewUserViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, ViewUserViewModel viewUserViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.viewUserViewModel = viewUserViewModel;
    }


    @Override
    public void prepareSuccessView(SearchOutputData output) {
        SearchState searchState = searchViewModel.getState();
        viewUserViewModel.getState().setUsername(output.getUserFound());

        searchState.setSearchError(null);
        searchState.setSuccess(true);

        
        this.searchViewModel.setState(searchState);
        searchViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setSearchError(error);
        searchState.setSuccess(false);
        searchViewModel.firePropertyChanged();

    }
}
