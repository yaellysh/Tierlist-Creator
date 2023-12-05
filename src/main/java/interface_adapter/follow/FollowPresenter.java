package interface_adapter.follow;

import data_access.FileUserDataAccessObject;
import factory.FollowFactory;
import interface_adapter.ViewManagerModel;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowOutputData;
import use_case.view_user.ViewUserOutputBoundary;
import use_case.view_user.ViewUserOutputData;
import view.FollowView;

public class FollowPresenter implements FollowOutputBoundary{

    private final FollowViewModel followViewModel;
    private final ViewManagerModel viewManagerModel;

    public FollowPresenter(ViewManagerModel viewManagerModel, FollowViewModel followViewModel) {
        this.followViewModel = followViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(FollowOutputData output) {
        // On success, pop up mutual users.

        FollowState followState = followViewModel.getState();
        followState.setRelatedUsers(output.getRelatedUsers());
        followState.setIsFollowing(output.getFollow());

        this.followViewModel.setState(followState);

        System.out.println(followState.getIsFollowing() + " afterward");
        followViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(followViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}