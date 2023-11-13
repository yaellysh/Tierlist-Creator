package interface_adapter.tierlist;

import entity.TierAdapter;
import entity.User;
import use_case.tierlist.TierListInputData;
import use_case.tierlist.TierListInteractor;

public class TierListController {
    final TierListInteractor tierListInteractor;

    public TierListController(TierListInteractor tierListInteractor) {
        this.tierListInteractor = tierListInteractor;
    }

    public void execute(User user, String tierList, String item, String newTier) {
        TierListInputData tierListInputData = new TierListInputData(
                user.getUsername(),
                tierList,
                item,
                TierAdapter.getTiers().get(newTier)
        );
        tierListInteractor.execute(tierListInputData);
    }
}
