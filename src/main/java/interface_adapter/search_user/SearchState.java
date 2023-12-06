package interface_adapter.search_user;

public class SearchState {
    private String search = "";
    private String searchError = null;

    public String getSearch() {
        return search;
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


}
