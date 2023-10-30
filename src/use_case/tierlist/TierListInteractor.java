package use_case.tierlist;

import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;

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
        // Precondition: user, tierList and item exist.
        String user = data.getUser();
        String tierList = data.getTierList();
        String item = data.getItem();
        Tier tier = data.getTier();

        dataAccessInterface.saveTier(user, tierList, item, tier);

        TierListOutputData outputData = new TierListOutputData(item, tier);

        outputBoundary.prepareSuccessView(outputData);
    }
}
