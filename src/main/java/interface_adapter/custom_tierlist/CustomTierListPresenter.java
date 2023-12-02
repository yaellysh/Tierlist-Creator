package interface_adapter.custom_tierlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import use_case.generate.custom_tierlist.CustomTierListOutputBoundary;
import use_case.generate.custom_tierlist.CustomTierListOutputData;

public class CustomTierListPresenter implements CustomTierListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CustomTierListViewModel customTierListViewModel;
    private final TierListViewModel tierListViewModel;
    public CustomTierListPresenter(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel, TierListViewModel tierListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.customTierListViewModel = customTierListViewModel;
        this.tierListViewModel = tierListViewModel;

    }
    @Override
    public void prepareSuccessView(CustomTierListOutputData data) {
        TierListState tierListState = new TierListState(data.getUser(), data.getTierList());
    }

    @Override
    public void prepareFailView() {

    }
}
