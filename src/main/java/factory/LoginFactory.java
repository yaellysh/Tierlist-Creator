package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.selector.SelectorViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import view.LoginView;

import javax.swing.*;

import data_access.FileUserDataAccessObject;

import java.io.IOException;

public class LoginFactory {

    /** Prevent instantiation. */
    private LoginFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoginDataAccessInterface userDataAccessObject,
            SelectorViewModel selectorViewModel) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, userDataAccessObject, selectorViewModel);
            return new LoginView(loginViewModel, loginController, new MenuViewModel());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoginDataAccessInterface userDataAccessObject, SelectorViewModel selectorViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel, selectorViewModel);

        LoginInputBoundary loginInteractor = new LoginInteractor((FileUserDataAccessObject) userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
