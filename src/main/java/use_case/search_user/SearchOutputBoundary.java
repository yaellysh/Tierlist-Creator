package use_case.search_user;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData searchOutputData);
    void prepareFailView(String error);
}
