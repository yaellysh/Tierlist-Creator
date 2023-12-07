package use_case.search_user;

import use_case.follow.FollowOutputData;

public class SearchInteractor implements SearchInputBoundary {

    final SearchUserDataAccessInterface userDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchUserDataAccessInterface userDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary) {
        userDataAccessObject = userDataAccessInterface;
        searchPresenter = searchOutputBoundary;
    }
    public void execute(SearchInputData searchInputData) {
        String search = searchInputData.getSearch();

        if (!userDataAccessObject.existsByName(search)) {
            searchPresenter.prepareFailView("No accounts exist by the username " + search);
        }

        else {
            SearchOutputData searchOutputData = new SearchOutputData(false, search);
            searchPresenter.prepareSuccessView(searchOutputData);
        }

    }
}
