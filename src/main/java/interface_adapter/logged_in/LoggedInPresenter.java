package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import use_case.logged_in.LoggedInOutputBoundary;

public class LoggedInPresenter implements LoggedInOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void returnToMenu(MenuViewModel menuViewModel) {
        this.viewManagerModel.setActiveView(menuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
