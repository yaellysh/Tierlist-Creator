package view;

import javax.swing.*;

public class LabelDropDownPanel extends JPanel {

    private final JComboBox<String> dropDown;
    private final String labelName;

    LabelDropDownPanel(JLabel label, String[] items) {
        this.labelName = label.getText();
//        this.dropDown = new JComboBox<>(
//                Arrays.stream(TierAdapter.TIERS).map(TierAdapter::getName).toArray(String[]::new));
        this.dropDown = new JComboBox<>(items);
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
