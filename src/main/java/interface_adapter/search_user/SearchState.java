package interface_adapter.search_user;

import entity.User;

public class SearchState {
    private String search = "";
    private String searchError = null;
    private boolean success = false;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private User currentUser;

    public String getSearch() {
        return search;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
