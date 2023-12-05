package use_case.signup;

import interface_adapter.menu.MenuViewModel;

public interface SignupInputBoundary {
    void execute(SignupInputData signupInputData);
    void returnToMenu(MenuViewModel menuViewModel);
}
