package use_case.view_user;

public class ViewUserInteractor implements ViewUserInputBoundary {
    final ViewUserDataAccessInterface userDataAccessObject;
    final ViewUserOutputBoundary viewUserPresenter;

    public ViewUserInteractor(ViewUserDataAccessInterface userDataAccessInterface,
                              ViewUserOutputBoundary viewUserOutputBoundary) {
        userDataAccessObject = userDataAccessInterface;
        viewUserPresenter = viewUserOutputBoundary;
    }

    public void execute(ViewUserInputData viewUserInputData) {

    }
}
