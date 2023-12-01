package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.follow.FollowPresenter;
import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListPresenter;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import use_case.follow.FollowInteractor;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.tierlist.TierListInteractor;
import use_case.tierlist.TierListOutputBoundary;
import view.FollowView;
import view.TierListView;

public class FollowFactory {

    private FollowFactory() {
    }

    public static FollowView create(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, FollowUserDataAccessInterface userDataAccessObject) {

        FollowController followController = createFollowUseCase(viewManagerModel, followViewModel, userDataAccessObject);
        //followViewModel.setState(new FollowState()); // TODO: currently hardcoded, will change when login is implemented
        return new FollowView(followController, followViewModel);
    }
    private static FollowController createFollowUseCase(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, FollowUserDataAccessInterface userDataAccessObject) {
        FollowOutputBoundary followOutputBoundary = new FollowPresenter(viewManagerModel, followViewModel);
        FollowInteractor followInteractor = new FollowInteractor(userDataAccessObject, followOutputBoundary);
        return new FollowController(followInteractor);
    }
}