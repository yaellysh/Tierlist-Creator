package use_case.search_user;

public class SearchOutputData {
    private boolean useCaseFailed;
    private final String userFound;

    public SearchOutputData(boolean useCaseFailed, String user) {
        this.useCaseFailed = useCaseFailed;
        userFound = user;
    }

    public String getUserFound() {
        return userFound;
    }
}
