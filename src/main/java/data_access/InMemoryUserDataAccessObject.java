package data_access;

import entity.User;
import use_case.follow.FollowUserDataAccessInterface;
import use_case.search_user.SearchUserDataAccessInterface;
import use_case.view_user.ViewUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements FollowUserDataAccessInterface, ViewUserDataAccessInterface {
    private final Map<String, User> accounts = new HashMap<>();

    @Override
    public User getUser(String username) {
        return this.accounts.get(username);
    }

    public void updateUsers() {}

    public void save(User user) {
        accounts.put(user.getUsername(), user);
    }

}
