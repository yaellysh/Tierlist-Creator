package use_case.custom_tierlist;

import entity.User;

public class CustomTierListInputData {

    private final User user;
    private final String listName;
    private final String[] inputs;

    public CustomTierListInputData(User user, String listName, String[] inputs) {
        this.user = user;
        this.listName = listName;
        this.inputs = inputs;
    }

    public User getUser() {
        return user;
    }

    public String getListName() {
        return listName;
    }

    public String[] getInputs() {
        return inputs;
    }
}
