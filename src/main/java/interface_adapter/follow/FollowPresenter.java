package interface_adapter.follow;

import interface_adapter.ViewManagerModel;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowOutputData;

public class FollowPresenter implements FollowOutputBoundary{

    private final FollowViewModel followViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ViewUserViewModel viewUserViewModel;
    private final TierListViewModel tierListViewModel;

    public FollowPresenter(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, ViewUserViewModel viewUserModel, TierListViewModel tierListViewModel) {
        this.followViewModel = followViewModel;
        this.viewUserViewModel = viewUserModel;
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
            viewUserViewModel.getState().setNumFollowers(output.getNewFollowers());

            this.followViewModel.setState(followState);

            followViewModel.firePropertyChanged();
            viewManagerModel.setActiveView(followViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }

    }

}