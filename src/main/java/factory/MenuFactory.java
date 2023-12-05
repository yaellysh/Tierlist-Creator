package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import view.MenuView;

public class MenuFactory {
    private MenuFactory() {}

    public static MenuView create(
            ViewManagerModel viewManagerModel, MenuViewModel menuViewModel, LoginViewModel loginViewModel,
            SignupViewModel signupViewModel) {
        MenuController menuController = createMenuUseCase(viewManagerModel, signupViewModel, loginViewModel);
        return new MenuView(menuController, menuViewModel);
    }

    private static MenuController createMenuUseCase(ViewManagerModel viewManagerModel,
                                                    SignupViewModel signupViewModel, LoginViewModel loginViewModel) {

        // Notice how we pass this method's parameters to the Presenter.
        MenuOutputBoundary menuOutputBoundary = new MenuPresenter(viewManagerModel, signupViewModel, loginViewModel);

        MenuInputBoundary menuInteractor = new MenuInteractor(menuOutputBoundary);

        return new MenuController(menuInteractor);
    }
}
