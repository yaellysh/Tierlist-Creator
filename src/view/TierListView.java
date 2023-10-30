package view;

import interface_adapter.tierlist.TierListController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TierListView extends JPanel implements ActionListener {

    public final String viewName = "tier list";

    private final TierListViewModel tierListViewModel;
    private final TierListController tierListController;

    public TierListView(TierListViewModel tierListViewModel) {
        this.tierListViewModel = tierListViewModel;
    }
    public TierListView(TierListController tierListController, TierListViewModel tierListViewModel) {
        this.tierListController = tierListController;
        this.tierListViewModel = tierListViewModel;

        JLavel title = new JLabel(interface_adapter.tierlist.TierListViewModel.TITLE_LABEL);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
