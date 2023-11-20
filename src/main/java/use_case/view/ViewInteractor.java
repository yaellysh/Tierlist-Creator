package use_case.view;

public class ViewInteractor implements ViewInputBoundary {

    public final ViewOutputBoundary outputBoundary;

    public ViewInteractor(ViewOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ViewInputData data) {
        outputBoundary.prepareSuccessView(new ViewOutputData(data.getUser(), data.getTierList()));
    }
}
