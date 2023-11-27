package use_case.generate.random_tierlist;

import entity.Item;
import entity.TierList;
import entity.User;
import use_case.generate.GenerateTierListDataAccessInterface;

import java.util.List;

public class RandomTierListInteractor implements RandomTierListInputBoundary {

    private final RandomTierListDataAccessInterface randomTierListDataAccessInterface;
    private final GenerateTierListDataAccessInterface generateTierListDataAccessInterface;
    private final RandomTierListOutputBoundary outputBoundary;

    public RandomTierListInteractor(RandomTierListDataAccessInterface dataAccessInterface,
                                    GenerateTierListDataAccessInterface dataAccessInterface1,
                                    RandomTierListOutputBoundary outputBoundary) {
        this.randomTierListDataAccessInterface = dataAccessInterface;
        this.generateTierListDataAccessInterface = dataAccessInterface1;
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void execute(RandomTierListInputData data) {
        String prompt = data.getPrompt();
        User user = data.getUser();
        List<Item> items = randomTierListDataAccessInterface.generateTierList(prompt);

        if (items == null) {
            outputBoundary.prepareFailView();
            return;
        }

        TierList list = new TierList(prompt, items);
        generateTierListDataAccessInterface.addTierList(user, list);
        outputBoundary.prepareSuccessView(new RandomTierListOutputData(user, prompt));

        // Pass it on.
    }
}
