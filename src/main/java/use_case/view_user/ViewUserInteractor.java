package use_case.view_user;

import entity.User;

import java.util.List;

public class ViewUserInteractor implements ViewUserInputBoundary {
    final ViewUserDataAccessInterface userDataAccessObject;
    final ViewUserOutputBoundary viewUserPresenter;

    public ViewUserInteractor(ViewUserDataAccessInterface userDataAccessInterface,
                              ViewUserOutputBoundary viewUserOutputBoundary) {
        userDataAccessObject = userDataAccessInterface;
        viewUserPresenter = viewUserOutputBoundary;
    }

    public void execute(ViewUserInputData viewUserInputData) {
        // get the selected user from input data
        String selectedUsername = viewUserInputData.getSelectedUser();
        User selectedUser = userDataAccessObject.getUser(selectedUsername);

        int followerCount = selectedUser.getFollowers().size();
        int followingCount = selectedUser.getFollowing().size();
        List<String> tierLists = selectedUser.getTierListsAsStrings();

        ViewUserOutputData viewUserOutputData = new ViewUserOutputData(tierLists, followerCount, followingCount);
        viewUserPresenter.prepareSuccessView(viewUserOutputData);
    }
}
