package interface_adapter.view_user;

import interface_adapter.ViewModel;

public class ViewUserViewModel extends ViewModel {
    public static final String FOLLOWING_LABEL = "Following";
    public static final String FOLLOWERS_LABEL = "Followers";

    public static final String TIERLIST_LABEL = "Tier Lists";

    private ViewUserState state = new ViewUserState();

    public ViewUserViewModel() {
        super("view user");
    }
}
