package use_case.generate.custom_tierlist;

import entity.Item;
import entity.TierList;
import use_case.generate.GenerateTierListDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class CustomTierListInteractor implements CustomTierListInputBoundary {

    private final CustomTierListOutputBoundary outputBoundary;
    private final GenerateTierListDataAccessInterface dataAccessInterface;

    public CustomTierListInteractor(CustomTierListOutputBoundary outputBoundary,
                                    GenerateTierListDataAccessInterface dataAccessInterface) {
        this.outputBoundary = outputBoundary;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void execute(CustomTierListInputData data) {
        String[] inputs = data.getInputs();
        List<Item> items = new ArrayList<>();

        // TODO: Fail them if they did not offer enough strings or if the list name already exists

        for (String input : inputs) {
            Item item = new Item(input);
            items.add(item);
        }

        TierList list = new TierList(data.getListName(), items);
        this.dataAccessInterface.addTierList(data.getUser(), list);
        this.outputBoundary.prepareSuccessView(new CustomTierListOutputData(
                data.getUser().getUsername(),
                data.getListName()));
    }
}
