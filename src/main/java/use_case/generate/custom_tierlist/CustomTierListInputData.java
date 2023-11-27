package use_case.generate.custom_tierlist;

import entity.User;

public class CustomTierListInputData {

    private final User user;
    private final String[] inputs;

    public CustomTierListInputData(User user, String[] inputs) {
        this.user = user;
        this.inputs = inputs;
    }

    public User getUser() {
        return user;
    }

    public String[] getInputs() {
        return inputs;
    }
}
