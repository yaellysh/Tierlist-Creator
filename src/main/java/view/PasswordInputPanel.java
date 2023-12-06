package view;

import interface_adapter.selector.SelectorViewModel;

import javax.swing.*;
import java.awt.*;

public class PasswordInputPanel extends JPanel {
    private final JPasswordField textField;
    PasswordInputPanel(String itemName) {
        this.textField = new JPasswordField();
        this.add(new JLabel(itemName));

        textField.setFont(SelectorViewModel.BUTTON_FONT);
        textField.setPreferredSize(new Dimension(200, 25));
        this.setMaximumSize(new Dimension(600, 25));
        this.add(textField);
    }

    public JPasswordField getTextField() {
        return textField;
    }
}
