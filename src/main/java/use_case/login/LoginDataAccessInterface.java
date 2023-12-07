package use_case.login;

import entity.User;

public interface LoginDataAccessInterface {
    boolean existsByName(String identifier);

    User getUser(String username);
}
