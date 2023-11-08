package view;

import entity.TierAdapter;

import javax.swing.*;
import java.util.Arrays;

public class LabelDropDownPanel extends JPanel {

    private final JComboBox<String> dropDown;

    LabelDropDownPanel(JLabel label){
        this.dropDown = new JComboBox<>(
                Arrays.stream(TierAdapter.TIERS).map(TierAdapter::getName).toArray(String[]::new));
        this.add(label);
        this.add(dropDown);
    }
    public JComboBox getDropDown(){
        return this.dropDown;
    }
}
