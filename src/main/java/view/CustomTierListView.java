package view;

import entity.TierList;
import interface_adapter.custom_tierlist.CustomTierListController;
import interface_adapter.custom_tierlist.CustomTierListState;
import interface_adapter.custom_tierlist.CustomTierListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class CustomTierListView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "custom";
    public final CustomTierListController customTierListController;
    public final CustomTierListViewModel customTierListViewModel;

    public CustomTierListView(CustomTierListController customTierListController, CustomTierListViewModel customTierListViewModel, TierListView tierListView) {
        this.customTierListController = customTierListController;
        this.customTierListViewModel = customTierListViewModel;
//        customTierListViewModel.addPropertyChangeListener(this);

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
        titleInput.getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getSource().equals(titleInput.getTextField())) {
                    CustomTierListState currentState = customTierListViewModel.getState();
                    String text = titleInput.getTextField().getText() + e.getKeyChar();
                    currentState.setTitle(text);
                    customTierListViewModel.setState(currentState);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.add(titleInput);
        this.add(new JSeparator());
        this.setBorder(null);
        this.add(Box.createRigidArea(new Dimension(600, -150)));

        JPanel largePanel = new JPanel();
        largePanel.setLayout(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setMaximumSize(new Dimension(super.getWidth(), 200));
        JPanel rightPanel = new JPanel();

        List<InputPanel> itemInputs = new ArrayList<>();

        for (int i = 0; i < TierList.LENGTH; i++) {
            InputPanel itemInput = new InputPanel("Item " + (i + 1));
            itemInputs.add(itemInput);
            if (i < TierList.LENGTH / 2) {
                leftPanel.add(itemInput);
            } else {
                rightPanel.add(itemInput);
            }
            int finalI = i;
            itemInput.getTextField().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getSource().equals(itemInput.getTextField())) {
                        CustomTierListState currentState = customTierListViewModel.getState();
                        currentState.addItem(itemInput.getTextField().getText() + e.getKeyChar(), finalI);
                        customTierListViewModel.setState(currentState);
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }
        ;


        this.add(largePanel);
        largePanel.add(leftPanel);
        largePanel.add(rightPanel);

        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel);

        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);
        submitButton.setOpaque(true);
        submitButton.setPreferredSize(new Dimension(250, 50));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);
        backButton.setOpaque(true);
        backButton.setPreferredSize(new Dimension(250, 50));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tierListView.actionPerformed(e);
                if (e.getSource().equals(submitButton)) {
                    CustomTierListState state = customTierListViewModel.getState();
                    customTierListController.execute(
                            state.getItems(),
                            state.getUser(),
                            state.getTitle()
                    );
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tierListView.actionPerformed(e);
                if (e.getSource().equals(backButton)) {
                    customTierListController.execute();

                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CustomTierListState state = (CustomTierListState) evt.getNewValue();
        System.out.println("did this get triggered?");
        if (state.getEmptyError() != null) {
            JOptionPane.showMessageDialog(this, state.getEmptyError());
        }

    }
}
