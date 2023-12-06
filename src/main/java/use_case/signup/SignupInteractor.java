package use_case.signup;

import entity.User;
import interface_adapter.menu.MenuViewModel;

import java.util.Objects;

public class SignupInteractor implements SignupInputBoundary {
    final SignupDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else if (Objects.equals(signupInputData.getUsername(), "") || Objects.equals(signupInputData.getPassword(), "") || Objects.equals(signupInputData.getRepeatPassword(), "")) {
            userPresenter.prepareFailView("Please ensure no inputs are empty.");
        } else {

            User user = new User(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.addUser(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void returnToMenu(MenuViewModel menuViewModel) {
            userPresenter.returnToMenu(menuViewModel);
    }
}
