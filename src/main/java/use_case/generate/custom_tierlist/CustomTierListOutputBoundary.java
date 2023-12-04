package use_case.generate.custom_tierlist;

public interface CustomTierListOutputBoundary {

    void prepareSuccessView(CustomTierListOutputData data);

    void prepareFailView(String error);

    void prepareBackView();
}
