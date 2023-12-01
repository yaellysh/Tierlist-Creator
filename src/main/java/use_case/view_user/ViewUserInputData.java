package use_case.view_user;

public class ViewUserInputData {
    private final String selectedUser;

    public ViewUserInputData(String selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getSelectedUser() {
        return selectedUser;
    }
}
