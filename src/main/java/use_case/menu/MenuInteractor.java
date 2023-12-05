package use_case.menu;

public class MenuInteractor implements MenuInputBoundary {

    final MenuOutputBoundary menuPresenter;

    public MenuInteractor(MenuOutputBoundary menuOutputBoundary) {
        this.menuPresenter = menuOutputBoundary;
    }

    @Override
    public void execute(MenuInputData menuInputData) {
        if (menuInputData.getSelected().equals("signup")) {
            menuPresenter.prepareSignupView();
        }
        else {
            menuPresenter.prepareLoginView();
        }
    }
}
