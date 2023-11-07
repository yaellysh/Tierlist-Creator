package view;

import javax.swing.*;

public class LabelDropDownPanel extends JPanel {
    LabelDropDownPanel(JLabel label, JComboBox<String> dropDown){
        this.add(label);
        this.add(dropDown);
    }
}
