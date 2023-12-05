package use_case.signup;

import interface_adapter.menu.MenuViewModel;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(String error);
    void returnToMenu(MenuViewModel menuViewModel);
}