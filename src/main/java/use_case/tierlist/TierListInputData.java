package use_case.tierlist;

import entity.Tier;
import entity.User;

public class TierListInputData {

    private final String tierList;

    public TierListInputData(String user, String tierList, String item, Tier newTier) {
        this.tierList = tierList;
    }


    public String getTierList() {
        return tierList;
    }
}
