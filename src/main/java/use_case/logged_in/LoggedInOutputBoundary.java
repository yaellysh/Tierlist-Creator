package use_case.logged_in;

import interface_adapter.menu.MenuViewModel;

public interface LoggedInOutputBoundary {
    void returnToMenu(MenuViewModel menuViewModel);
}
