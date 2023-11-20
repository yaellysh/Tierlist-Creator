package view;

import interface_adapter.selector.SelectorController;
import interface_adapter.selector.SelectorState;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectorView extends JPanel implements ActionListener {
    public final String viewName = "tier list selector";
    public final SelectorController selectorController;
    public final SelectorViewModel selectorViewModel;
    public SelectorView(SelectorController selectorController, SelectorViewModel selectorViewModel) {
        this.selectorController = selectorController;
        this.selectorViewModel = selectorViewModel;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        // added title with padding
        JLabel selectorTitleLabel = new JLabel(SelectorViewModel.TITLE_LABEL);
        selectorTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        selectorTitleLabel.setFont(SelectorViewModel.TITLE_FONT);
        selectorTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(selectorTitleLabel);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
