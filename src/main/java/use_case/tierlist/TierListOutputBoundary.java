package use_case.tierlist;

public interface TierListOutputBoundary {

    void prepareSuccessView(TierListOutputData data);

    void prepareFailView(String error);

    void prepareBackView();
}
