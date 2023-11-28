package interface_adapter.random_tierlist;

import use_case.generate.random_tierlist.RandomTierListInputBoundary;

public class RandomTierListController {
    final RandomTierListInputBoundary randomInteractor;
    public RandomTierListController(RandomTierListInputBoundary randomInteractor){
        this.randomInteractor = randomInteractor;
    }
//    public void execute(String prompt, User user) {
//        randomInteractor.execute(new RandomTierListInputData(prompt, user));
//    }
}
