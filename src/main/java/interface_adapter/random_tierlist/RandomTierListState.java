package interface_adapter.random_tierlist;

import entity.User;

public class RandomTierListState {
    public User user;
    public String prompt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public RandomTierListState(User user, String prompt){
        this.user = user;
        this.prompt = prompt;
    }
}
