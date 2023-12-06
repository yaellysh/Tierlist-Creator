package interface_adapter.random_tierlist;

import entity.User;

public class RandomTierListState {
    public User user;
    public String prompt;
    public String error = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public RandomTierListState(){
    }
}
