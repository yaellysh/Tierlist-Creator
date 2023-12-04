package interface_adapter.follow;

import data_access.FileUserDataAccessObject;
import factory.FollowFactory;
import interface_adapter.ViewManagerModel;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowOutputData;
import view.FollowView;

public class FollowPresenter implements FollowOutputBoundary {

    private final FollowViewModel followViewModel;
    private final ViewManagerModel viewManagerModel;

    public FollowPresenter(ViewManagerModel viewManagerModel, FollowViewModel followViewModel) {
        this.followViewModel = followViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(FollowOutputData data) {
        // On success, pop up mutual users.

        FollowState followState = followViewModel.getState();
        followState.setRelatedUsers(data.getRelatedUsers());
        followState.setIsFollowing(data.getFollow());
        this.followViewModel.setState(followState);

        System.out.println(followState.getIsFollowing() + " afterward");
        followViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(followViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }
}