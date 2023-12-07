package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowPresenter;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.tierlist.TierListViewModel;
import use_case.follow.FollowInteractor;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowDataAccessInterface;
import view.FollowView;

public class FollowFactory {

    private FollowFactory() {
    }

    public static FollowView create(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, FollowDataAccessInterface userDataAccessObject, TierListViewModel tierListViewModel) {

        FollowController followController = createFollowUseCase(viewManagerModel, followViewModel, userDataAccessObject, tierListViewModel);
        return new FollowView(followController, followViewModel, tierListViewModel, viewManagerModel);
    }
    private static FollowController createFollowUseCase(ViewManagerModel viewManagerModel, FollowViewModel followViewModel,  FollowDataAccessInterface userDataAccessObject, TierListViewModel tierListViewModel) {
        FollowOutputBoundary followOutputBoundary = new FollowPresenter(viewManagerModel, followViewModel, tierListViewModel);
        FollowInteractor followInteractor = new FollowInteractor(userDataAccessObject, followOutputBoundary);
        return new FollowController(followInteractor);
    }
}