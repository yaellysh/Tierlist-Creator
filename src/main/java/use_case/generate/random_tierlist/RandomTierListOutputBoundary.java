package use_case.generate.random_tierlist;

public interface RandomTierListOutputBoundary {

    void prepareSuccessView(RandomTierListOutputData data);

    void prepareFailView();
    void prepareBackView();

}
