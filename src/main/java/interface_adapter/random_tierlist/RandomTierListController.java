package interface_adapter.random_tierlist;

import entity.User;
import use_case.generate.random_tierlist.RandomTierListInputBoundary;
import use_case.generate.random_tierlist.RandomTierListInputData;

public class RandomTierListController {
    final RandomTierListInputBoundary randomInteractor;
    public RandomTierListController(RandomTierListInputBoundary randomInteractor){
        this.randomInteractor = randomInteractor;
    }

    public void execute(String prompt, User user) {
        randomInteractor.execute(new RandomTierListInputData(prompt, user));
    }

    public void execute() {
        randomInteractor.execute();
    }
}
