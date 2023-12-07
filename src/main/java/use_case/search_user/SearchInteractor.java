package use_case.search_user;

import entity.User;
import use_case.follow.FollowOutputData;
import use_case.view_user.ViewUserOutputData;

import java.util.List;

public class SearchInteractor implements SearchInputBoundary {

    final SearchUserDataAccessInterface userDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchUserDataAccessInterface userDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary) {
        userDataAccessObject = userDataAccessInterface;
        searchPresenter = searchOutputBoundary;
    }
    public void execute(SearchInputData searchInputData) {
        String search = searchInputData.getSearch();

        if (!userDataAccessObject.existsByName(search)) {
            System.out.println("user not found?");
            searchPresenter.prepareFailView("No accounts exist by the username " + search);
        }

        else {
            System.out.println("user found");
            SearchOutputData searchOutputData = new SearchOutputData(false, search);

            String selectedUsername = searchInputData.getSearch();
            User selectedUser = this.userDataAccessObject.getUser(searchInputData.getSearch());;
            int followerCount = selectedUser.getFollowers().size();
            int followingCount = selectedUser.getFollowing().size();
            List<String> tierLists = selectedUser.getTierListsAsStrings();

            searchPresenter.prepareSuccessView(searchOutputData);

//            ViewUserOutputData viewUserOutputData = new ViewUserOutputData(selectedUsername, tierLists, followerCount, followingCount);
//            viewUserPresenter.prepareSuccessView(viewUserOutputData);

//            searchPresenter.prepareSuccessView(searchOutputData);
        }

    }
}
