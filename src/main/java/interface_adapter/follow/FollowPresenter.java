package interface_adapter.follow;

import interface_adapter.ViewManagerModel;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowOutputData;

public class FollowPresenter implements FollowOutputBoundary{

    private final FollowViewModel followViewModel;
    private final ViewManagerModel viewManagerModel;
    private final TierListViewModel tierListViewModel;

    public FollowPresenter(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, TierListViewModel tierListViewModel) {
        this.followViewModel = followViewModel;
        this.viewManagerModel = viewManagerModel;
        this.tierListViewModel = tierListViewModel;
    }


    @Override
    public void prepareSuccessView(FollowOutputData output) {

        if (output.getTierListName() != null) {
            TierListState tierListState = tierListViewModel.getState();
            tierListState.setTierList(output.getTierListName());
            tierListState.setUser(output.getCurrentUser());
            tierListState.setViewUser(output.getViewUser());
            tierListViewModel.setState(tierListState);
            tierListViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(tierListViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else {
            FollowState followState = followViewModel.getState();
            followState.setIsFollowing(output.getFollow());

            this.followViewModel.setState(followState);

            followViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(followViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }

    }

}