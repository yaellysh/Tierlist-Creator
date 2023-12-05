package use_case.view_existing;

public class ViewExistingInteractor implements ViewExistingInputBoundary {

    public final ViewExistingOutputBoundary outputBoundary;

    public ViewExistingInteractor(ViewExistingOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ViewExistingInputData data) {
        outputBoundary.prepareSuccessView(new ViewExistingOutputData(data.getUser(), data.getTierList()));
    }
    @Override
    public void execute() {
        outputBoundary.prepareBackView();
    }
}
