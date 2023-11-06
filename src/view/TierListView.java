package view;

import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TierListView extends JPanel implements ActionListener {

    public final String viewName = "tier list";

    private final TierListViewModel tierListViewModel;
    private final TierListController tierListController;

    public TierListView(TierListController tierListController, TierListViewModel tierListViewModel) {
        this.tierListController = tierListController;
        this.tierListViewModel = tierListViewModel;

        JLabel title = new JLabel(interface_adapter.tierlist.TierListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
            JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
}