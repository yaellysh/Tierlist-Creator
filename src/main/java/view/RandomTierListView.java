package view;

import interface_adapter.random_tierlist.RandomTierListController;
import interface_adapter.random_tierlist.RandomTierListViewModel;
import interface_adapter.selector.SelectorViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomTierListView  extends JPanel implements ActionListener {
    public final String viewName = "random tier list maker";
    public RandomTierListController randomTierListController;
    public RandomTierListViewModel randomTierListViewModel;
    public RandomTierListView(RandomTierListController randomTierListController, RandomTierListViewModel randomTierListViewModel) {
        this.randomTierListController = randomTierListController;
        this.randomTierListViewModel = randomTierListViewModel;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        JLabel randomTitleLabel = new JLabel(RandomTierListViewModel.TITLE_LABEL);
        randomTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        randomTitleLabel.setFont(SelectorViewModel.TITLE_FONT);
        this.add(randomTitleLabel);
        randomTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel();
        instructions.setText("<html>" + RandomTierListViewModel.INSTRUCTIONS + "</html>");
        instructions.setFont(SelectorViewModel.TEXT_FONT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        this.add(instructions);

        JPanel inputWrapper = new JPanel();
        JTextField input = new JTextField();
        inputWrapper.add(input);
        this.add(inputWrapper);
        input.setPreferredSize(new Dimension(300, 40));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
