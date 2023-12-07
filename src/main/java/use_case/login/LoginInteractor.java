package use_case.login;

import data_access.FileUserDataAccessObject;
import entity.User;
import interface_adapter.menu.MenuViewModel;

public class LoginInteractor implements LoginInputBoundary {
    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(FileUserDataAccessObject object,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = object;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.getUser(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.getUser(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(user, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void returnToMenu(MenuViewModel menuViewModel) {
        loginPresenter.returnToMenu(menuViewModel);
    }
}