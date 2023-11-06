package factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListViewModel;
import view.TierListView;

import javax.swing.*;
import java.io.IOException;

public class TierListFactory {

    private TierListFactory() {
    }

    public static TierListView create(ViewManagerModel viewManagerModel, TierListViewModel tierListViewModel) {

        try {
            TierListController tierListController = createTierListUseCase(viewManagerModel, tierListViewModel);
            return new TierListView(tierListController, tierListViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Idk what to put ehre");
        }
    }
}
