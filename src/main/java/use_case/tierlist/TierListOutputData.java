package use_case.tierlist;

import entity.Tier;

public class TierListOutputData {

    private final String item;
    private final Tier tier;
    private final String user;
    private final String tierList;

    public TierListOutputData(String item, Tier tier, String user, String tierList) {
        this.item = item;
        this.tier = tier;
        this.user = user;
        this.tierList = tierList;
    }

    public String getItem() {
        return item;
    }

    public Tier getTier() {
        return tier;
    }

    public String getUser() {
        return user;
    }

    public String getTierList() {
        return tierList;
    }

}
