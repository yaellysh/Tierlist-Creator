package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.menu.MenuViewModel;
import use_case.logged_in.LoggedInInputBoundary;
import use_case.logged_in.LoggedInInteractor;
import use_case.logged_in.LoggedInOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoggedInView;

public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory() {}

    public static LoggedInView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        LoggedInController loggedInController = createLoggedInUseCase(viewManagerModel, loggedInViewModel);
        return new LoggedInView(loggedInViewModel, loggedInController);
    }

    private static LoggedInController createLoggedInUseCase(ViewManagerModel viewManagerModel,
                                                            LoggedInViewModel loggedInViewModel) {
        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(viewManagerModel, loggedInViewModel);

        LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(loggedInOutputBoundary);

        return new LoggedInController(loggedInInteractor);
    }
}
