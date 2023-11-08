package view;

import entity.Tier;
import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TierListView extends JPanel implements ActionListener {

    public final String viewName = "tier list";

    LabelPanel tier;

    public final TierListController tierListController;
    public final TierListViewModel tierListViewModel;

    public TierListView(TierListController tierListController, TierListViewModel tierListViewModel) {

        this.tierListViewModel = tierListViewModel;
        this.tierListController = tierListController;

        // setting up the box layout to help formatting
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        // added title with padding
        JLabel tierListTitleLabel = new JLabel(TierListViewModel.TITLE_LABEL);
        tierListTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        tierListTitleLabel.setFont(TierListViewModel.TITLE_FONT);
        this.add(tierListTitleLabel);

        // setting up the actual tier list using a JPanel with a GridLayout
        GridLayout grid = new GridLayout(TierListViewModel.NUM_TIERS, 10);
        JPanel tierGrid = new JPanel();
        tierGrid.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        tierGrid.setLayout(grid);
        this.add(tierGrid);

        // changing the colours of the grid boxes based on their tier
        // this will be changed later when input data is actually brought in
        // then the colour of the box will be dependent on if there is anything in it
        for (int i = 0; i < TierListViewModel.NUM_TIERS * 10; i++) {
            Tier currentTier = Tier.TIERS[i / 10];

            // creates tier labels for first column, otherwise empty boxes
            // also adds colours to the tiers
            if (i % 10 == 0) {
                tier = new LabelPanel(new JLabel(currentTier.getName()));
                tier.setBackground(Color.LIGHT_GRAY);
            } else {
                tier = new LabelPanel(new JLabel());
                tier.setBackground(currentTier.getColor());
            }

            tier.setBorder(BorderFactory.createLineBorder(Color.black));
            tier.setPreferredSize(new Dimension(50, 50));
            tierGrid.add(tier);

        }

        // added instructions label
        // the <html> tags ensure the instructions wrap to the screen
        JLabel instructions = new JLabel();
        instructions.setText("<html>" + TierListViewModel.INSTRUCTIONS + "</html>");
        instructions.setFont(TierListViewModel.TEXT_FONT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 20));
        this.add(instructions);

        // setting up the container the two frames will be placed in
        GridLayout doubleFrame = new GridLayout(1, 2);

        JPanel dropDownFramePanel = new JPanel();
        this.add(dropDownFramePanel);
        dropDownFramePanel.setLayout(doubleFrame);

        // setting up the two frames to organise the dropdowns into
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder());

        dropDownFramePanel.add(leftPanel);
        dropDownFramePanel.add(rightPanel);

        for (int i = 0; i < 10; i++) {
            LabelDropDownPanel item = new LabelDropDownPanel(new JLabel("Item " + (i + 1)), new JComboBox<>(
                    Arrays.stream(Tier.TIERS).map(Tier::getName).toArray(String[]::new)));
            item.setMaximumSize(new Dimension(200, 40));
            if (i < 5) {
                leftPanel.add(item);
            } else {
                rightPanel.add(item);
            }

        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JButton generateButton = new JButton(TierListViewModel.GENERATE_BUTTON);
        generateButton.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(generateButton);
        this.add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
}