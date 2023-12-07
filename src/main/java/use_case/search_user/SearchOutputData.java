package use_case.search_user;

import entity.User;

import java.util.List;

public class SearchOutputData {
    private boolean useCaseFailed;

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public User getUser() {
        return user;
    }

    public List<String> getTierLists() {
        return tierLists;
    }

    private User user;
    private List<String> tierLists;

    public SearchOutputData(boolean useCaseFailed, User user, List<String> tierLists) {
        this.useCaseFailed = useCaseFailed;
        this.user = user;
        this.tierLists = tierLists;

    }

}
