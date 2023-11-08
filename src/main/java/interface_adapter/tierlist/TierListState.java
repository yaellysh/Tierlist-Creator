package interface_adapter.tierlist;

import entity.Tier;

public class TierListState {
    private String user = "";
    private String tierList = "";
    private String item = "";
    private Tier tier;

    TierListState() {
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTierList() {
        return this.tierList;
    }

    public void setTierList(String tierList) {
        this.tierList = tierList;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Tier getTier() {
        return this.tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }


}
