package interface_adapter.tierlist;

import entity.TierAdapter;
import entity.User;
import use_case.tierlist.TierListInputData;
import use_case.tierlist.TierListInputBoundary;

public class TierListController {
    final TierListInputBoundary tierListInteractor;

    public TierListController(TierListInputBoundary tierListInteractor) {
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
    public void execute() {
        tierListInteractor.execute();
    }


}
