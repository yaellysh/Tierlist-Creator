package interface_adapter.view_existing;

import entity.User;
import use_case.view_existing.ViewExistingInputBoundary;
import use_case.view_existing.ViewExistingInputData;

public class ViewExistingController {
    final ViewExistingInputBoundary viewExistingInteractor;
    public ViewExistingController(ViewExistingInputBoundary viewExistingInteractor) {
        this.viewExistingInteractor = viewExistingInteractor;
    }
    public void execute(String title, User user) {
        viewExistingInteractor.execute(new ViewExistingInputData(user, title));
    }
    public void execute() {
        viewExistingInteractor.execute();
    }
}
