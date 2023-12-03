package view;

import interface_adapter.selector.SelectorController;
import interface_adapter.selector.SelectorState;
import interface_adapter.selector.SelectorViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SelectorView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "selector";
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
        this.add(selectorTitleLabel);
        selectorTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions1 = new JLabel();
        instructions1.setText("<html>" + SelectorViewModel.INSTRUCTIONS1 + "</html>");
        instructions1.setFont(SelectorViewModel.TEXT_FONT);
        instructions1.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions1.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        this.add(instructions1);

        ButtonPanel viewButtonPanel = new ButtonPanel(SelectorViewModel.BUTTONS.get(0), SelectorViewModel.COLORS.get(0));
        this.add(viewButtonPanel);
        viewButtonPanel.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(viewButtonPanel.getButton())) {
                    SelectorState state = selectorViewModel.getState();

                    selectorController.execute(
                            viewButtonPanel.getButton().getText(),
                            state.getUser());
                }
            }
        });

        this.add(new JSeparator());

        JLabel instructions2 = new JLabel();
        instructions2.setText("<html>" + SelectorViewModel.INSTRUCTIONS2 + "</html>");
        instructions2.setFont(SelectorViewModel.TEXT_FONT);
        instructions2.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions2.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        this.add(instructions2);

        for (int i = 1; i < 3; i++) {

            ButtonPanel buttonPanel = new ButtonPanel(SelectorViewModel.BUTTONS.get(i), SelectorViewModel.COLORS.get(i));
            this.add(buttonPanel);
            buttonPanel.getButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(buttonPanel.getButton())) {
                        SelectorState state = selectorViewModel.getState();

                        selectorController.execute(
                                buttonPanel.getButton().getText(),
                                state.getUser());
                    }
                }
            });
        }
        this.add(new JSeparator());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SelectorState state = (SelectorState) evt.getNewValue();
        state.setUser(((SelectorState) evt.getNewValue()).getUser());
    }
}
