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
        String username = data.getUser();
        String tierList = data.getTierList();
        String item = data.getItem();
        Tier tier = data.getTier();
        User user = dataAccessInterface.getUser(username);

        // Update item with new tier.

        user.getTierList(tierList)
                .getItem(item)
                .setTier(tier);
        dataAccessInterface.save();

        TierListOutputData outputData = new TierListOutputData(user, tierList);
        outputBoundary.prepareSuccessView(outputData);
    }
    public void execute(){
        outputBoundary.prepareBackView();
    }
}
