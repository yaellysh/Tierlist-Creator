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

        // setting up the box layout to help formatting
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        // added title with padding
        JLabel tierListTitleLabel = new JLabel("Tier List Example");
        tierListTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        tierListTitleLabel.setFont(new Font("Arial Bold", Font.PLAIN, 25));
        this.add(tierListTitleLabel);

        // setting up the actual tier list using a JPanel with a GridLayout
        GridLayout grid = new GridLayout(4, 10);
        JPanel tierGrid = new JPanel();
        tierGrid.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // setting the size of the tier list s.t. the boxes are square
        int width = this.getWidth();
        int height = this.getHeight() / 2;
        tierGrid.setPreferredSize(new Dimension(width, height));

        tierGrid.setLayout(grid);
        this.add(tierGrid);

        // changing the colours of the grid boxes based on their tier
        // this will be changed later when input data is actually brought in
        // then the colour of the box will be dependent on if there is anything in it
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
            tier.setPreferredSize(new Dimension(50, 50));
            tierGrid.add(tier);

        }

        // added instructions label
        // the <html> tags ensure the instructions wrap to the screen
        JLabel instructions = new JLabel();
        instructions.setText("<html>In the drop down options below please select the tier you wish to place each item" +
                " in.\n To view the corresponding tier list, press the 'generate' button.</html>");
        instructions.setFont(new Font("Arial", Font.PLAIN, 15));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        this.add(instructions);

        // setting up the container the two frames will be placed in
        GridLayout doubleFrame = new GridLayout(1, 2);
        JPanel dropDownFramePanel = new JPanel();
        this.add(dropDownFramePanel);
        dropDownFramePanel.setLayout(doubleFrame);

        // setting up the two frames to organise the dropdowns into
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