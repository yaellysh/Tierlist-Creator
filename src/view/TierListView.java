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
    LabelTextPanel tier;

    public TierListView(TierListController tierListController, TierListViewModel tierListViewModel) {
        this.tierListController = tierListController;
        this.tierListViewModel = tierListViewModel;

        GridLayout grid = new GridLayout(4, 10);
        JPanel tierGrid = new JPanel();
        this.add(tierGrid);

        tierGrid.setLayout(grid);

        for (int i = 0; i < 40; i++) {
            if (i==0) {tier = new LabelTextPanel(new JLabel("S"));}
            else if (i==10) {tier = new LabelTextPanel(new JLabel("A"));}
            else if (i==20) {tier = new LabelTextPanel(new JLabel("B"));}
            else if (i==30) {tier = new LabelTextPanel(new JLabel("C"));}
            else {tier = new LabelTextPanel(new JLabel());}

            if (i%10 == 0) {tier.setBackground(Color.lightGray);}
            else if (i < 10) {tier.setBackground(Color.red);}
            else if (i < 20) {tier.setBackground(Color.orange);}
            else if (i < 30) {tier.setBackground(Color.yellow);}
            else {tier.setBackground(Color.green);}

            tier.setBorder(BorderFactory.createLineBorder(Color.black));
            tier.setSize(20, 20);
            tierGrid.add(tier);
        }



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
}