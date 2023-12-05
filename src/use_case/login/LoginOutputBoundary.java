package use_case.login;

import interface_adapter.menu.MenuViewModel;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(String error);
    void returnToMenu(MenuViewModel menuViewModel);
}