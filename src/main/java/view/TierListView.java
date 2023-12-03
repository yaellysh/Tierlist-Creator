package view;

import entity.Tier;
import entity.TierAdapter;
import entity.TierList;
import entity.User;
import interface_adapter.tierlist.TierListController;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;

public class TierListView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "tier list";
    public final TierListController tierListController;
    public final TierListViewModel tierListViewModel;
    LabelPanel tier;

    public TierListView(TierListController tierListController, TierListViewModel tierListViewModel) {

        this.tierListViewModel = tierListViewModel;
        this.tierListController = tierListController;
        tierListViewModel.addPropertyChangeListener(this);


        // setting up the box layout to help formatting
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        // added title with padding
        JLabel tierListTitleLabel = new JLabel(TierListViewModel.TITLE_LABEL);
        tierListTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        tierListTitleLabel.setFont(TierListViewModel.TITLE_FONT);
        tierListTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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
`
        loadGrid(tierGrid);

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

        TierListState currentState = tierListViewModel.getState();
        User user = currentState.getUser();
        TierList tierList = user.getTierList(currentState.getTierList());
        List<Map.Entry<String, Tier>> entries = tierList.getTierList().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .toList();
        System.out.println(currentState.getTierList());

        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<String, Tier> entry = entries.get(i);
            LabelDropDownPanel item = new LabelDropDownPanel(new JLabel(entry.getKey()));
            item.getDropDown().setSelectedItem(entry.getValue().toString());
            item.setMaximumSize(new Dimension(200, 40));
            if (i < (TierListViewModel.NUM_ITEMS + 1) / 2) {
                leftPanel.add(item);
            } else {
                rightPanel.add(item);
            }

            item.getDropDown().addActionListener(
                    e -> {
                        if (e.getSource().equals(item.getDropDown())) {
                            TierListState currentState1 = tierListViewModel.getState();

                            tierListController.execute(
                                    currentState1.getUser(),
                                    currentState1.getTierList(),
                                    item.getName(),
                                    Objects.requireNonNull(item.getDropDown().getSelectedItem()).toString()
                            );
                            tierListViewModel.setState(currentState1);
                            tierGrid.removeAll();
                            loadGrid(tierGrid);
                            tierGrid.revalidate();
                            tierGrid.repaint();
                        }
                    }
            );

        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JButton homeButton = new JButton(TierListViewModel.HOME_BUTTON);
        homeButton.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(homeButton);
        this.add(buttonPanel);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO: go back a screen

            }
        });

    }

    private void loadGrid(JPanel tierGrid) {

        TierListState currentState = tierListViewModel.getState();
        User user = currentState.getUser();
        Map<Tier, ArrayList<String>> tierItemMap = new HashMap<>();
        TierList tierList = user.getTierList(currentState.getTierList());

        for (String item : tierList.getTierList().keySet()) {
            if (tierItemMap.containsKey(tierList.getTierList().get(item))) {
                tierItemMap.get(tierList.getTierList().get(item)).add(item);
            } else {
                tierItemMap.put(tierList.getTierList().get(item), new ArrayList<>(Collections.singletonList(item)));
            }
        }

        for (int i = 0; i < TierListViewModel.NUM_TIERS; i++) {
            TierAdapter currentTier = TierAdapter.TIERS[i];
            // creates tier labels for first column, otherwise empty boxes
            // also adds colours to the tiers

            tier = new LabelPanel(new JLabel(currentTier.getName()));
            tier.setBackground(Color.LIGHT_GRAY);
            tier.setBorder(BorderFactory.createLineBorder(Color.black));
            tier.setPreferredSize(new Dimension(50, 50));
            tierGrid.add(tier);

            if (tierItemMap.get(currentTier.getTier()) != null) {
                int lenTier = tierItemMap.get(currentTier.getTier()).size();
                for (int j = 0; j < lenTier; j++) {
                    tier = new LabelPanel(new JLabel(tierItemMap.get(currentTier.getTier()).get(j)));
                    tier.setBackground(currentTier.getColor());
                    tier.setBorder(BorderFactory.createLineBorder(Color.black));
                    tier.setPreferredSize(new Dimension(50, 50));
                    tierGrid.add(tier);

                }
                for (int j = lenTier; j < TierListViewModel.NUM_ITEMS; j++) {
                    tier = new LabelPanel(new JLabel());
                    tier.setBackground(Color.LIGHT_GRAY);
                    tier.setBorder(BorderFactory.createLineBorder(Color.black));
                    tier.setPreferredSize(new Dimension(50, 50));
                    tierGrid.add(tier);
                }
            } else {
                for (int j = 0; j < TierListViewModel.NUM_ITEMS; j++) {
                    tier = new LabelPanel(new JLabel());
                    tier.setBackground(Color.LIGHT_GRAY);
                    tier.setBorder(BorderFactory.createLineBorder(Color.black));
                    tier.setPreferredSize(new Dimension(50, 50));
                    tierGrid.add(tier);
                }

            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TierListState state = (TierListState) evt.getNewValue();
        state.setUser(((TierListState) evt.getNewValue()).getUser());
        state.setTierList(((TierListState) evt.getNewValue()).getTierList());
    }

}