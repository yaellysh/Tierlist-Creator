package use_case.search_user;

import entity.User;

public interface SearchUserDataAccessInterface {
    boolean existsByName(String username);
    User getUser(String name);
}
