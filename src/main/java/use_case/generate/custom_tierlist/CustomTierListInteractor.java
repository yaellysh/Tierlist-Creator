package use_case.generate.custom_tierlist;

import entity.Item;
import entity.TierList;
import entity.User;
import use_case.tierlist.TierListDataAccessInterface;

import java.util.Arrays;
import java.util.List;

public class CustomTierListInteractor implements CustomTierListInputBoundary {

    private final CustomTierListOutputBoundary outputBoundary;
    private final TierListDataAccessInterface dataAccessInterface;

    public CustomTierListInteractor(CustomTierListOutputBoundary outputBoundary,
                                    TierListDataAccessInterface dataAccessInterface) {
        this.outputBoundary = outputBoundary;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void execute(CustomTierListInputData data) {
        String[] inputs = data.getInputs();
        String name = data.getListName();
        User user = data.getUser();

        if (name == null) {
            this.outputBoundary.prepareFailView("Your tierlist needs a name. Please try again.");
        } else if (user.getTierList(name) != null) {
            this.outputBoundary.prepareFailView("A tierlist already exists with that name. Please try again.");
            return;
        } else if (inputs.length != TierList.LENGTH) {
            this.outputBoundary.prepareFailView("At least one of your inputs is empty. Please try again.");
            return;
        } else {
            List<Item> items = Arrays.stream(inputs).map(Item::new).toList();

            TierList list = new TierList(name, items);
            user.addTierList(list);
            dataAccessInterface.save();
            this.outputBoundary.prepareSuccessView(new CustomTierListOutputData(user, name));
        }
    }

    @Override
    public void execute() {

        outputBoundary.prepareBackView();
    }
}
