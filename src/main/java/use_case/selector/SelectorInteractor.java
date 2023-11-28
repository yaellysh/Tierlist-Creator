package use_case.selector;

public class SelectorInteractor implements SelectorInputBoundary {

    public final SelectorOutputBoundary outputBoundary;

    public SelectorInteractor(SelectorOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SelectorInputData data) {
        outputBoundary.prepareSuccessView(new SelectorOutputData(data.getLabel(), data.getUser()));
    }
}
