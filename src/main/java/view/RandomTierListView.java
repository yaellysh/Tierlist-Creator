package view;

import interface_adapter.random_tierlist.RandomTierListController;
import interface_adapter.random_tierlist.RandomTierListState;
import interface_adapter.random_tierlist.RandomTierListViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RandomTierListView extends JPanel implements ActionListener {
    public final String viewName = "random tier list maker";
    public RandomTierListController randomTierListController;
    public RandomTierListViewModel randomTierListViewModel;

    public RandomTierListView(RandomTierListController randomTierListController, RandomTierListViewModel randomTierListViewModel) {
        this.randomTierListController = randomTierListController;
        this.randomTierListViewModel = randomTierListViewModel;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        JLabel randomTitleLabel = new JLabel(RandomTierListViewModel.TITLE_LABEL);
        randomTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        randomTitleLabel.setFont(RandomTierListViewModel.TITLE_FONT);
        this.add(randomTitleLabel);
        randomTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel();
        instructions.setText("<html>" + RandomTierListViewModel.INSTRUCTIONS + "</html>");
        instructions.setFont(RandomTierListViewModel.TEXT_FONT_LARGE);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        this.add(instructions);

        JLabel examples = new JLabel();
        examples.setText("<html>" + RandomTierListViewModel.EG + "</html>");
        examples.setFont(RandomTierListViewModel.TEXT_FONT_SMALL);
        examples.setAlignmentX(Component.CENTER_ALIGNMENT);
        examples.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        this.add(examples);

        JPanel inputWrapper = new JPanel();
        JTextField inputField = new JTextField();
        inputWrapper.add(inputField);
        inputWrapper.setLayout(new BoxLayout(inputWrapper, BoxLayout.Y_AXIS));
        this.add(inputWrapper);
        inputField.setPreferredSize(new Dimension(300, 40));
        inputWrapper.setBorder(new EmptyBorder(20, 20, 250, 20));

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton(RandomTierListViewModel.SUBMIT_BUTTON);
        buttonPanel.add(submitButton);
        submitButton.setOpaque(true);
//        submitButton.setFont(RandomTierListViewModel.BUTTON_FONT);
        submitButton.setPreferredSize(new Dimension(250, 50));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        inputWrapper.add(buttonPanel);
        inputWrapper.add(new JSeparator());

        inputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RandomTierListState currentState = randomTierListViewModel.getState();
                        String text = inputField.getText() + e.getKeyChar();
                        currentState.setPrompt(text);
                        randomTierListViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        submitButton.addActionListener(e -> {
            if (e.getSource().equals(submitButton)) {
                RandomTierListState state = randomTierListViewModel.getState();

                randomTierListController.execute(
                        "list the names of 10 popular " + inputField.getText(),
                        state.getUser());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
