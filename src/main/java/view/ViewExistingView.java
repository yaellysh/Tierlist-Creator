package view;

import entity.TierList;
import interface_adapter.view_existing.ViewExistingController;
import interface_adapter.view_existing.ViewExistingState;
import interface_adapter.view_existing.ViewExistingViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ViewExistingView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "view existing";
    public ViewExistingController viewExistingController;

    public ViewExistingView(ViewExistingController viewExistingController, ViewExistingViewModel viewExistingViewModel) {
        this.viewExistingController = viewExistingController;
        this.viewExistingViewModel = viewExistingViewModel;
        viewExistingViewModel.addPropertyChangeListener(this);

        this.updateScreen();

    }

    private void updateScreen() {
        removeAll();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        JLabel existingTitle = new JLabel(ViewExistingViewModel.TITLE_LABEL);
        existingTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        existingTitle.setFont(ViewExistingViewModel.TITLE_FONT);
        this.add(existingTitle);
        existingTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel();
        instructions.setText("<html>" + ViewExistingViewModel.INSTRUCTIONS + "</html>");
        instructions.setFont(ViewExistingViewModel.TEXT_FONT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        this.add(instructions);


        java.util.List<TierList> tierlistObjects = viewExistingViewModel.getState().getUser().getTierLists();
        List<String> items = new ArrayList<>();
        this.add(new JSeparator());

        for (TierList tierlist: tierlistObjects) {
            items.add(tierlist.getName());
        }
        LabelDropDownPanel tierlistDropDown = new LabelDropDownPanel(new JLabel("Tierlists: "), new HashSet<>(items).toArray(new String[0]));
        this.add(tierlistDropDown);
        tierlistDropDown.setBorder(new EmptyBorder(0,10,-10,10));
        this.add(Box.createRigidArea(new Dimension(50, -30)));

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton(ViewExistingViewModel.SUBMIT_BUTTON);

        submitButton.setOpaque(true);
        submitButton.setPreferredSize(new Dimension(250, 50));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton backButton = new JButton(ViewExistingViewModel.BACK_BUTTON);
        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);
        backButton.setOpaque(true);
        backButton.setPreferredSize(new Dimension(250, 50));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.add(buttonPanel);
        this.add(new JSeparator());

        submitButton.addActionListener(e -> {
            if (e.getSource().equals(submitButton)) {
                ViewExistingState state = viewExistingViewModel.getState();

                viewExistingController.execute((String) tierlistDropDown.getDropDown().getSelectedItem(), state.getUser());
            }
        });

        backButton.addActionListener(e -> {
            if (e.getSource().equals(backButton)) {
                viewExistingController.execute();

            }
        });
    }

    public ViewExistingViewModel viewExistingViewModel;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.updateScreen();

    }
}
