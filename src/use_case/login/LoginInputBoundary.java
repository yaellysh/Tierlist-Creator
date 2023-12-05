package use_case.login;

import interface_adapter.menu.MenuViewModel;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);
    void returnToMenu(MenuViewModel menuViewModel);
}
