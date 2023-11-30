package interface_adapter.custom_tierlist;

import entity.User;
import use_case.generate.custom_tierlist.CustomTierListInputBoundary;
import use_case.generate.custom_tierlist.CustomTierListInputData;

import java.util.ArrayList;

public class CustomTierListController {
    final CustomTierListInputBoundary customInteractor;

    public CustomTierListController(CustomTierListInputBoundary customTierListInteractor) {
        this.customInteractor = customTierListInteractor;
    }

    public void execute(ArrayList<String> item, User user) {
        customInteractor.execute(new CustomTierListInputData(item, user));
    }
}
