package view;

import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TierListView extends JPanel implements ActionListener {

    public final String viewName = "tier list";

    LabelTextPanel tier;

    public final TierListController tierListController;
    public final TierListViewModel tierListViewModel;

    public TierListView(TierListController tierListController, TierListViewModel tierListViewModel) {

        this.tierListViewModel = tierListViewModel;
        this.tierListController = tierListController;

        JLabel tierListTitleLabel = new JLabel("Tier List Example");
        tierListTitleLabel.setFont(new Font("Arial Bold", Font.PLAIN, 20));

        LabelTextPanel tierListTitle = new LabelTextPanel(tierListTitleLabel);
        this.add(tierListTitle);

        GridLayout grid = new GridLayout(4, 10);
        JPanel tierGrid = new JPanel();
        this.add(tierGrid);

        tierGrid.setLayout(grid);

        for (int i = 0; i < 40; i++) {

            if (i == 0) {
                tier = new LabelTextPanel(new JLabel("S"));
            } else if (i == 10) {
                tier = new LabelTextPanel(new JLabel("A"));
            } else if (i == 20) {
                tier = new LabelTextPanel(new JLabel("B"));
            } else if (i == 30) {
                tier = new LabelTextPanel(new JLabel("C"));
            } else {
                tier = new LabelTextPanel(new JLabel());
            }

            if (i % 10 == 0) {
                tier.setBackground(Color.lightGray);
            } else if (i < 10) {
                tier.setBackground(Color.red);
            } else if (i < 20) {
                tier.setBackground(Color.orange);
            } else if (i < 30) {
                tier.setBackground(Color.yellow);
            } else {
                tier.setBackground(Color.green);
            }

            tier.setBorder(BorderFactory.createLineBorder(Color.black));
            tier.setPreferredSize(new Dimension(55, 55));
            tierGrid.add(tier);

        }
        JLabel itemsTitleLabel = new JLabel("Items");
        itemsTitleLabel.setFont(new Font("Arial Bold", Font.PLAIN, 20));

        LabelTextPanel itemsTitle = new LabelTextPanel(itemsTitleLabel);
        this.add(itemsTitle);

        GridLayout doubleFrame = new GridLayout(1, 2);
        JPanel dropDownFramePanel = new JPanel();
        dropDownFramePanel.setPreferredSize(new Dimension(600, 162));
        this.add(dropDownFramePanel);

        dropDownFramePanel.setLayout(doubleFrame);
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        dropDownFramePanel.add(leftPanel);
        dropDownFramePanel.add(rightPanel);
        leftPanel.setBackground(Color.red);
        rightPanel.setBackground(Color.blue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
}