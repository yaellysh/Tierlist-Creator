package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowController;
import interface_adapter.follow.FollowPresenter;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.view_user.ViewUserController;
import interface_adapter.view_user.ViewUserPresenter;
import interface_adapter.view_user.ViewUserViewModel;
import use_case.follow.FollowInteractor;
import use_case.follow.FollowOutputBoundary;
import use_case.follow.FollowDataAccessInterface;
import use_case.view_user.ViewUserDataAccessInterface;
import use_case.view_user.ViewUserInteractor;
import use_case.view_user.ViewUserOutputBoundary;
import view.FollowView;

public class FollowFactory {

    private FollowFactory() {
    }

    public static FollowView create(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, ViewUserViewModel viewUserViewModel, FollowDataAccessInterface userDataAccessObject, ViewUserDataAccessInterface viewUserDAO) {

        FollowController followController = createFollowUseCase(viewManagerModel, followViewModel, viewUserViewModel, userDataAccessObject);
        ViewUserController viewUserController = createViewUserUseCase(viewManagerModel, viewUserViewModel, followViewModel, viewUserDAO);
        return new FollowView(followController, followViewModel);
    }
    private static FollowController createFollowUseCase(ViewManagerModel viewManagerModel, FollowViewModel followViewModel, ViewUserViewModel viewUserViewModel, FollowDataAccessInterface userDataAccessObject) {
        FollowOutputBoundary followOutputBoundary = new FollowPresenter(viewManagerModel, followViewModel, viewUserViewModel);
        FollowInteractor followInteractor = new FollowInteractor(userDataAccessObject, followOutputBoundary);
        return new FollowController(followInteractor);
    }

    private static ViewUserController createViewUserUseCase(ViewManagerModel viewManagerModel, ViewUserViewModel viewUserViewModel, FollowViewModel followViewModel, ViewUserDataAccessInterface userDataAccessObject) {
        ViewUserOutputBoundary viewUserOutputBoundary = new ViewUserPresenter(viewManagerModel, viewUserViewModel, followViewModel);
        ViewUserInteractor viewUserInteractor = new ViewUserInteractor(userDataAccessObject, viewUserOutputBoundary);
        return new ViewUserController(viewUserInteractor);
    }
}