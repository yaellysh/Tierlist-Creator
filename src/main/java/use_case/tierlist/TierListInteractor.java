package use_case.tierlist;

import entity.Tier;
import entity.User;

public class TierListInteractor implements TierListInputBoundary {

    private final TierListDataAccessInterface dataAccessInterface;
    private final TierListOutputBoundary outputBoundary;

    public TierListInteractor(TierListDataAccessInterface dataAccessInterface,
                              TierListOutputBoundary outputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(TierListInputData data) {
        // Precondition: user, tierList and item exist.
        User user = data.getUser();
        String tierList = data.getTierList();

        dataAccessInterface.saveTier(user, tierList);

        TierListOutputData outputData = new TierListOutputData(user, tierList);

        outputBoundary.prepareSuccessView(outputData);
    }
}
