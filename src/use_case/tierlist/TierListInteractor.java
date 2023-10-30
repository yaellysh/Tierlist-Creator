package use_case.tierlist;

public class TierListInteractor implements TierListInputBoundary {

    private TierListDataAccessInterface dataAccessInterface;
    private TierListOutputBoundary outputBoundary;

    public TierListInteractor(TierListDataAccessInterface dataAccessInterface,
                              TierListOutputBoundary outputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(TierListInputData data) {

    }
}
