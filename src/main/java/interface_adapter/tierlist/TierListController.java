package interface_adapter.tierlist;

import entity.Tier;
import use_case.tierlist.TierListInputBoundary;
import use_case.tierlist.TierListInputData;
import use_case.tierlist.TierListInteractor;

public class TierListController {
    final TierListInteractor tierListInteractor;

    public TierListController(TierListInteractor tierListInteractor) {
        this.tierListInteractor = tierListInteractor;
    }

    public void execute(String user, String tierList, String item, Tier tier) {
        TierListInputData tierListInputData = new TierListInputData(
                user,
                tierList,
                item,
                tier
        );
        tierListInteractor.execute(tierListInputData);
    }
}
