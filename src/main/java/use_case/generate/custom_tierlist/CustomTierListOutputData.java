package use_case.generate.custom_tierlist;

public class CustomTierListOutputData {

    private final String user;
    private final String tierList;

    public CustomTierListOutputData(String user, String tierList) {
        this.user = user;
        this.tierList = tierList;
    }

    public String getUser() {
        return user;
    }

    public String getTierList() {
        return tierList;
    }
}
