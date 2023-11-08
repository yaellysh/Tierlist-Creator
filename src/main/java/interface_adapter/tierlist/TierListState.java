package interface_adapter.tierlist;

import entity.Tier;
import entity.User;

public class TierListState {
    private User user;
    private String tierList;
    private Tier tier;

    TierListState(User user, String tierList) {
        this.user = user;
        this.tierList = tierList;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTierList() {
        return this.tierList;
    }

    public void setTierList(String tierList) {
        this.tierList = tierList;
    }

}
