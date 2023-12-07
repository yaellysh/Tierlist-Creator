package def;

import interface_adapter.menu.MenuViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class DefaultSignupOutputBoundary implements SignupOutputBoundary {
    @Override
    public void prepareSuccessView(SignupOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }

    @Override
    public void returnToMenu(MenuViewModel menuViewModel) {

    }
}
