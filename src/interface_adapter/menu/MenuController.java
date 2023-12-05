package interface_adapter.menu;

import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuInputData;

public class MenuController {

    final MenuInputBoundary menuInteractor;
    public MenuController(MenuInputBoundary menuInteractor) {
        this.menuInteractor = menuInteractor;
    }

    public void execute(String selected) {
        MenuInputData menuInputData = new MenuInputData(selected);
        menuInteractor.execute(menuInputData);
    }
}
