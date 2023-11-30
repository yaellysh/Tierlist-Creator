package interface_adapter.custom_tierlist;

import interface_adapter.ViewManagerModel;
import use_case.generate.custom_tierlist.CustomTierListOutputBoundary;
import use_case.generate.custom_tierlist.CustomTierListOutputData;

public class CustomTierListPresenter implements CustomTierListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CustomTierListViewModel customTierListViewModel;
    public CustomTierListPresenter(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.customTierListViewModel = customTierListViewModel;
    }
    @Override
    public void prepareSuccessView(CustomTierListOutputData data) {

    }

    @Override
    public void prepareFailView() {

    }
}
