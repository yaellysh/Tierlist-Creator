package view;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private final JButton button;
    private final String text;


    ButtonPanel(String text, Color color) {

        this.text = text;

        JPanel buttonPanel = new JPanel();
        this.button = new JButton(text);
        buttonPanel.add(button);

        button.setOpaque(true);
//        button.setBackground(color);
//        button.setFont(SelectorViewModel.BUTTON_FONT);
        button.setPreferredSize(new Dimension(400, 50));
        buttonPanel.setPreferredSize(new Dimension(400, 55));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(buttonPanel);
    }

    ButtonPanel(String text) {

        this.text = text;

        JPanel buttonPanel = new JPanel();
        this.button = new JButton(text);
        buttonPanel.add(button);

        button.setOpaque(true);
        buttonPanel.setPreferredSize(new Dimension(400, 55));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(buttonPanel);
    }

    public JButton getButton() {
        return button;
    }
    public String getText() {
        return this.text;
    }
}
