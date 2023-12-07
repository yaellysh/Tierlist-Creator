package interface_adapter.tierlist;

import entity.User;

public class TierListState {
    private User user;
    private String tierList;
    private String error = null;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
