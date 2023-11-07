package interface_adapter.tierlist;

import use_case.tierlist.TierListInputBoundary;
import use_case.tierlist.TierListInputData;

public class TierListController {
    final TierListInputBoundary tierListInputBoundary;

    public TierListController(TierListInputBoundary tierListInputBoundary) {
        this.tierListInputBoundary = tierListInputBoundary;
    }

    public void execute(TierListInputData tierListInputData) {
        tierListInputBoundary.execute(tierListInputData);
    }
}
