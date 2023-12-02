package use_case.generate.random_tierlist;

import data_access.ChatGPTDataAccessObject;
import data_access.FileUserDataAccessObject;
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
            System.out.println("fail");
            return;
        }

        TierList list = new TierList(prompt, items);
        user.addTierList(list);
        generateTierListDataAccessInterface.save();
        outputBoundary.prepareSuccessView(new RandomTierListOutputData(user, prompt));
    }

    public static void main(String[] args) {
        RandomTierListInteractor interactor = new RandomTierListInteractor(new ChatGPTDataAccessObject(),
                new FileUserDataAccessObject("src/main/resources/users.json"),
                new RandomTierListOutputBoundary() {
                    @Override
                    public void prepareSuccessView(RandomTierListOutputData data) {

                    }

                    @Override
                    public void prepareFailView() {

                    }
                });
        interactor.execute(new RandomTierListInputData("Generate a list of 9 popular movies",
                new User("Yael")));
    }

}
