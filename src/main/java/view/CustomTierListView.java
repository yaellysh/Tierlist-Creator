package view;

import interface_adapter.custom_tierlist.CustomTierListController;
import interface_adapter.custom_tierlist.CustomTierListState;
import interface_adapter.custom_tierlist.CustomTierListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomTierListView extends JPanel implements ActionListener {
    public final String viewName = "custom tier list maker";
    public final CustomTierListController customTierListController;
    public final CustomTierListViewModel customTierListViewModel;

    public CustomTierListView(CustomTierListController customTierListController, CustomTierListViewModel customTierListViewModel) {
        this.customTierListController = customTierListController;
        this.customTierListViewModel = customTierListViewModel;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(boxLayout);


        JLabel customTierListTitleLabel = new JLabel(CustomTierListViewModel.TITLE_LABEL);
        customTierListTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        customTierListTitleLabel.setFont(CustomTierListViewModel.TITLE_FONT);
        this.add(customTierListTitleLabel);
        customTierListTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel();
        instructions.setText("<html>" + CustomTierListViewModel.INSTRUCTIONS + "</html>");
        instructions.setFont(CustomTierListViewModel.TEXT_FONT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        this.add(instructions);

        InputPanel titleInput = new InputPanel("Title: ");
        this.add(titleInput);
        this.add(new JSeparator());
        this.setBorder(null);
        this.add(Box.createRigidArea(new Dimension(600, -150)));

        JPanel largePanel = new JPanel();
        largePanel.setLayout(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setMaximumSize(new Dimension(super.getWidth(), 200));
        JPanel rightPanel = new JPanel();

        for (int i = 0; i < 10; i++) {
            InputPanel itemInput = new InputPanel("Item " + (i + 1));
            if (i < 5) {
                leftPanel.add(itemInput);
            } else {
                rightPanel.add(itemInput);
            }

            itemInput.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    CustomTierListState currentState = customTierListViewModel.getState();
                    String text = itemInput.getTextField().getText() + e.getKeyChar();
                    currentState.addItem(text);
                    customTierListViewModel.setState(currentState);
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }

        this.add(largePanel);
        largePanel.add(leftPanel);
        largePanel.add(rightPanel);
        this.add(new ButtonPanel("Submit"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
