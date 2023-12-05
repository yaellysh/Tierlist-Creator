package interface_adapter.logged_in;

import interface_adapter.menu.MenuViewModel;
import use_case.logged_in.LoggedInInputBoundary;
import use_case.login.LoginInputBoundary;

public class LoggedInController {
    final LoggedInInputBoundary loggedInInteractor;
    public LoggedInController(LoggedInInputBoundary loggedInInteractor) {
        this.loggedInInteractor = loggedInInteractor;
    }

    public void returnToMenu(MenuViewModel menuViewModel) {
        loggedInInteractor.returnToMenu(menuViewModel);
    }
}
