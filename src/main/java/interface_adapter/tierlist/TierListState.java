package interface_adapter.tierlist;

import entity.User;

public class TierListState {
    private User user;
    private String tierList;

    public TierListState() {
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
