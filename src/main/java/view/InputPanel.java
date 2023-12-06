package view;

import interface_adapter.selector.SelectorViewModel;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    private final JTextField textField;
    InputPanel(String itemName) {
        this.textField = new JTextField();
        this.add(new JLabel(itemName));

        textField.setFont(SelectorViewModel.BUTTON_FONT);
        textField.setPreferredSize(new Dimension(200, 25));
        this.setMaximumSize(new Dimension(300, 25));
        this.add(textField);
    }

    public JTextField getTextField() {
        return textField;
    }
}
