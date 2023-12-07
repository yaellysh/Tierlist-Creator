package interface_adapter.follow;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowOutputData;

public class FollowPresenter implements FollowOutputBoundary{

    private final FollowViewModel followViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ViewUserViewModel viewUserViewModel;

    public FollowPresenter(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, ViewUserViewModel viewUserModel) {
        this.followViewModel = followViewModel;
        this.viewUserViewModel = viewUserModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(FollowOutputData output) {
        // On success, pop up mutual users.
        FollowState followState = followViewModel.getState();
//        followState.setRelatedUsers(output.getRelatedUsers());
        followState.setIsFollowing(output.getFollow());
        viewUserViewModel.getState().setNumFollowers(output.getNewFollowers());

        this.followViewModel.setState(followState);

        followViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(followViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}