package interface_adapter.tierlist;

import interface_adapter.ViewManagerModel;
import use_case.tierlist.TierListOutputBoundary;
import use_case.tierlist.TierListOutputData;

public class TierListPresenter implements TierListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final TierListViewModel tierListViewModel;

    public TierListPresenter(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.tierListViewModel = tierListViewModel;
    }
    @Override
    public void prepareSuccessView(TierListOutputData data) {

    }
}
