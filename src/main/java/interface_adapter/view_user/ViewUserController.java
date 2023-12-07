package interface_adapter.view_user;

import use_case.view_user.ViewUserInputBoundary;
import use_case.view_user.ViewUserInputData;

public class ViewUserController {
    final ViewUserInputBoundary viewUserUseCaseInteractor;

    public ViewUserController(ViewUserInputBoundary viewUserInputBoundary) {
        viewUserUseCaseInteractor = viewUserInputBoundary;
    }

    public void execute(String selectedUsername) {
        ViewUserInputData inputData = new ViewUserInputData(selectedUsername);
        viewUserUseCaseInteractor.execute(inputData);
    }
}
