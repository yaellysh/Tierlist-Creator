package view;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private final JButton button;


    ButtonPanel(String text) {

        JPanel buttonPanel = new JPanel();
        this.button = new JButton(text);
        buttonPanel.add(button);

        button.setOpaque(true);
        button.setPreferredSize(new Dimension(250, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(buttonPanel);
    }

    public JButton getButton() {
        return button;
    }

}
