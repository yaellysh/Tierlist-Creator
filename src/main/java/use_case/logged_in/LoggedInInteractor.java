package use_case.logged_in;

import interface_adapter.menu.MenuViewModel;

public class LoggedInInteractor implements LoggedInInputBoundary {
    final LoggedInOutputBoundary loggedInPresenter;

    public LoggedInInteractor(LoggedInOutputBoundary loggedInOutputBoundary) {
        this.loggedInPresenter = loggedInOutputBoundary;
    }

    @Override
    public void returnToMenu(MenuViewModel menuViewModel) {
        loggedInPresenter.returnToMenu(menuViewModel);
    }
}
