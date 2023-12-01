package use_case.view_user;

import entity.User;

public interface ViewUserDataAccessInterface {
    User getUser(String user);

}
