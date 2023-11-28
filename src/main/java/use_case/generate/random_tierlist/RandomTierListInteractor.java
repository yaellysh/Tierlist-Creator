package use_case.generate.random_tierlist;

public class RandomTierListInteractor implements RandomTierListInputBoundary {

    private final RandomTierListOutputBoundary outputBoundary;

    public RandomTierListInteractor(RandomTierListOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void execute(RandomTierListInputData data) {
        // Generate the tier list
        // Make sure it's right
        // Pass it on.
    }
}
