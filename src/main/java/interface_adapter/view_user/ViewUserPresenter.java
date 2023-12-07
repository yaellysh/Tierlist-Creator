package interface_adapter.view_user;

import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowViewModel;
import use_case.view_user.ViewUserOutputBoundary;
import use_case.view_user.ViewUserOutputData;

public class ViewUserPresenter implements ViewUserOutputBoundary {
    private final ViewUserViewModel viewUserViewModel;

    private final ViewManagerModel viewManagerModel;

    private final FollowViewModel followViewModel;

    public ViewUserPresenter(ViewManagerModel viewManagerModel,
                             ViewUserViewModel viewUserViewModel, FollowViewModel followViewModel) {
        this.viewUserViewModel = viewUserViewModel;
        this.viewManagerModel = viewManagerModel;
        this.followViewModel = followViewModel;
    }

    @Override
    public void prepareSuccessView(ViewUserOutputData output) {
        ViewUserState viewUserState = viewUserViewModel.getState();
        viewUserState.setNumFollowers(output.getNumFollowers());
        viewUserState.setNumFollowing(output.getNumFollowing());
        viewUserState.setTierLists(output.getTierLists());
        viewUserState.setUsername(output.getUsername());

        this.viewUserViewModel.setState(viewUserState);
//        followViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(viewUserViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }

}
