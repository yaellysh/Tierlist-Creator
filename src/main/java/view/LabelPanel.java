package view;

import javax.swing.*;

/**
 * A panel containing a label and a text field.
 */
class LabelPanel extends JPanel {
    JLabel label;

    public JLabel getLabel() {
        return label;
    }

    LabelPanel(JLabel label) {
        this.label = label;
        this.add(label);
    }
}
