package interface_adapter.follow;

import interface_adapter.ViewManagerModel;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowOutputData;

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
    }
}