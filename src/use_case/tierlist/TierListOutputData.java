package use_case.tierlist;

import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;

public class TierListOutputData {

    private final String item;
    private final Tier tier;

    public TierListOutputData(String item, Tier tier) {
        this.item = item;
        this.tier = tier;
    }

    public String getItem() {
        return item;
    }

    public Tier getTier() {
        return tier;
    }

}
