package interface_adapter.tierlist;

import entity.Tier;
import entity.User;
import use_case.tierlist.TierListInputBoundary;
import use_case.tierlist.TierListInputData;
import use_case.tierlist.TierListInteractor;

public class TierListController {
    final TierListInteractor tierListInteractor;

    public TierListController(TierListInteractor tierListInteractor) {
        this.tierListInteractor = tierListInteractor;
    }

    public void execute(User user, String tierList) {
        TierListInputData tierListInputData = new TierListInputData(
                user,
                tierList
        );
        tierListInteractor.execute(tierListInputData);
    }
}
