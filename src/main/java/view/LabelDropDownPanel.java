package view;

import entity.TierAdapter;

import javax.swing.*;
import java.util.Arrays;

public class LabelDropDownPanel extends JPanel {

    private final JComboBox<String> dropDown;
    private final String labelName;

    LabelDropDownPanel(JLabel label) {
        this.labelName = label.getText();
        this.dropDown = new JComboBox<>(
                Arrays.stream(TierAdapter.TIERS).map(TierAdapter::getName).toArray(String[]::new));
        this.add(label);
        this.add(dropDown);
    }

    public JComboBox<String> getDropDown() {
        return this.dropDown;
    }

    public String getName() {
        return this.labelName;
    }
}
