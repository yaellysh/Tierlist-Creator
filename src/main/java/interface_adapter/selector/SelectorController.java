package interface_adapter.selector;

import entity.User;
import use_case.selector.SelectorInputBoundary;

public class SelectorController {
    final SelectorInputBoundary selectorInteractor;

    public SelectorController(SelectorInputBoundary selectorInteractor) {
        this.selectorInteractor = selectorInteractor;
    }

    public void execute(User user) {
        selectorInteractor.execute();
    }
}
