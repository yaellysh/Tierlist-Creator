package interface_adapter.view_user;

import interface_adapter.ViewManagerModel;
import use_case.view_user.ViewUserOutputBoundary;
import use_case.view_user.ViewUserOutputData;

public class ViewUserPresenter implements ViewUserOutputBoundary {
    private final ViewUserViewModel viewUserViewModel;

    private final ViewManagerModel viewManagerModel;

    public ViewUserPresenter(ViewManagerModel viewManagerModel,
                             ViewUserViewModel viewUserViewModel) {
        this.viewUserViewModel = viewUserViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ViewUserOutputData output) {
        ViewUserState viewUserState = viewUserViewModel.getState();
        viewUserState.setNumFollowers(output.getNumFollowers());
        viewUserState.setNumFollowing(output.getNumFollowing());
        viewUserState.setTierLists(output.getTierLists());

        this.viewUserViewModel.setState(viewUserState);
        viewUserViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(viewUserViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }

}
