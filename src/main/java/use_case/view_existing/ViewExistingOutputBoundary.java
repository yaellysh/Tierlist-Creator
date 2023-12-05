package use_case.view_existing;

public interface ViewExistingOutputBoundary {

    void prepareSuccessView(ViewExistingOutputData data);
    void prepareFailView();

    void prepareBackView();
}
