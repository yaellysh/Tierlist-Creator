package use_case.tierlist;

import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;

public class TierListInputData {

    private final User user;
    private final TierList tierList;
    private final Item item;
    private final Tier tier;

    public TierListInputData(User user, TierList tierList, Item item, Tier tier) {
        this.user = user;
        this.tierList = tierList;
        this.item = item;
        this.tier = tier;
    }

    public User getUser() {
        return user;
    }

    public TierList getTierList() {
        return tierList;
    }

    public Item getItem() {
        return item;
    }

    public Tier getTier() {
        return tier;
    }
}
