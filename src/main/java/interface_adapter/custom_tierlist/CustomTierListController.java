package interface_adapter.custom_tierlist;

import entity.User;
import use_case.generate.custom_tierlist.CustomTierListInputBoundary;
import use_case.generate.custom_tierlist.CustomTierListInputData;

public class CustomTierListController {
    final CustomTierListInputBoundary customInteractor;

    public CustomTierListController(CustomTierListInputBoundary customTierListInteractor) {
        this.customInteractor = customTierListInteractor;
    }

    public void execute(String[] items, User user, String title) {
        customInteractor.execute(new CustomTierListInputData(user, title, items));
    }
}
