package interface_adapter.random_tierlist;

import interface_adapter.ViewManagerModel;
import use_case.generate.random_tierlist.RandomTierListOutputBoundary;
import use_case.tierlist.TierListOutputBoundary;
import use_case.tierlist.TierListOutputData;

public class RandomTierListPresenter implements RandomTierListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RandomTierListViewModel randomTierListViewModel;
    public RandomTierListPresenter(ViewManagerModel viewManagerModel, RandomTierListViewModel randomTierListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.randomTierListViewModel = randomTierListViewModel;
    }
    @Override
    public void prepareSuccessView(TierListOutputData data) {

    }
}
