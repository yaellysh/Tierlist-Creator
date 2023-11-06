package view;

import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListViewModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TierListView extends JPanel implements ActionListener {

    public final String viewName = "tier list";

    private final TierListViewModel tierListViewModel;
    private final TierListController tierListController;
    final JLabel pinkLabel;
    final JPanel pinkPanel;

    public TierListView(TierListController tierListController, TierListViewModel tierListViewModel) {
        this.tierListController = tierListController;
        this.tierListViewModel = tierListViewModel;

        JLabel title = new JLabel(interface_adapter.tierlist.TierListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel tiers = new JPanel();

        pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.pink);
        pinkLabel = new JLabel(TierListViewModel.PINK_LABEL);
        pinkPanel.add(pinkLabel);

        tiers.add(pinkLabel);
        tiers.add(pinkPanel);

        this.add(tiers);

//        JPanel buttons = new JPanel();
//        ok = new JButton(TierListViewModel.OK_BUTTON_LABEL);
//        buttons.add(ok);
//        ok.addActionListener(this);
//        this.add(buttons);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
            JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
}