package use_case.random_tierlist;

import entity.User;

public class RandomTierListInputData {

    private final String prompt;
    private final User user;

    public RandomTierListInputData(String prompt, User user) {
        this.prompt = prompt;
        this.user = user;
    }

    public String getPrompt() {
        return prompt;
    }

    public User getUser() {
        return user;
    }
}
