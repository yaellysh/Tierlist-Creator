package use_case.random_tierlist;

public interface RandomTierListOutputBoundary {

    void prepareSuccessView(RandomTierListOutputData data);

    void prepareFailView(String error);
    void prepareBackView();

}
