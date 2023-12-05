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
import interface_adapter.view_user.ViewUserController;
import interface_adapter.view_user.ViewUserPresenter;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.follow.FollowInteractor;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.tierlist.TierListInteractor;
import use_case.tierlist.TierListOutputBoundary;
import use_case.view_user.ViewUserDataAccessInterface;
import use_case.view_user.ViewUserInteractor;
import use_case.view_user.ViewUserOutputBoundary;
import view.FollowView;
import view.TierListView;

public class FollowFactory {

    private FollowFactory() {
    }

    public static FollowView create(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, ViewUserViewModel viewUserViewModel, FollowUserDataAccessInterface userDataAccessObject, ViewUserDataAccessInterface viewUserDAO) {

        FollowController followController = createFollowUseCase(viewManagerModel, followViewModel, userDataAccessObject);
        ViewUserController viewUserController = createViewUserUseCase(viewManagerModel, viewUserViewModel, viewUserDAO);
        //followViewModel.setState(new FollowState()); // TODO: currently hardcoded, will change when login is implemented
        return new FollowView(followController, followViewModel, viewUserController, viewUserViewModel);
    }
    
    private static FollowController createFollowUseCase(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, FollowUserDataAccessInterface userDataAccessObject) {
        FollowOutputBoundary followOutputBoundary = new FollowPresenter(viewManagerModel, followViewModel);
        FollowInteractor followInteractor = new FollowInteractor(userDataAccessObject, followOutputBoundary);
        return new FollowController(followInteractor);
    }

    private static ViewUserController createViewUserUseCase(ViewManagerModel viewManagerModel, ViewUserViewModel viewUserViewModel, ViewUserDataAccessInterface userDataAccessObject) {
        ViewUserOutputBoundary viewUserOutputBoundary = new ViewUserPresenter(viewManagerModel, viewUserViewModel);
        ViewUserInteractor viewUserInteractor = new ViewUserInteractor(userDataAccessObject, viewUserOutputBoundary);
        return new ViewUserController(viewUserInteractor);
    }
}