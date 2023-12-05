package view;

import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "menu";

    private final MenuViewModel menuViewModel;
    private final MenuController menuController;

    private final JButton signUp;
    private final JButton logIn;

    public MenuView(MenuController controller, MenuViewModel menuViewModel) {

        this.menuController = controller;
        this.menuViewModel = menuViewModel;
        menuViewModel.addPropertyChangeListener(this);

        JLabel menuTitleLabel = new JLabel(MenuViewModel.TITLE_LABEL);
        menuTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        menuTitleLabel.setFont(MenuViewModel.TITLE_FONT);
        this.add(menuTitleLabel);
        menuTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signupInstructions = new JLabel();
        menuTitleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        signupInstructions.setText("<html>" + MenuViewModel.SIGNUP_INSTRUCTIONS + "</html>");

        signupInstructions.setFont(MenuViewModel.TEXT_FONT);
        signupInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupInstructions.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 20));
        this.add(signupInstructions);
        this.add(Box.createRigidArea(new Dimension(10, -100)));

        ButtonPanel signupButtonPanel = new ButtonPanel(MenuViewModel.SIGNUP_BUTTON_LABEL);
        signupButtonPanel.setBorder(new EmptyBorder(100, 0, 0, 0));
        this.add(signupButtonPanel);
        this.add(new JSeparator());

        JLabel loginInstructions = new JLabel();
        this.add(Box.createRigidArea(new Dimension(10, -100)));
        loginInstructions.setText("<html>" + MenuViewModel.LOGIN_INSTRUCTIONS + "</html>");
        loginInstructions.setFont(MenuViewModel.TEXT_FONT);
        loginInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginInstructions.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 20));
        this.add(loginInstructions);

        ButtonPanel loginButtonPanel = new ButtonPanel(MenuViewModel.LOGIN_BUTTON_LABEL);
        this.add(loginButtonPanel);
        this.add(new JSeparator());

        this.signUp = signupButtonPanel.getButton();
        this.logIn = loginButtonPanel.getButton();

        signupButtonPanel.getButton().addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(signUp)) {
                        MenuState currentState = menuViewModel.getState();
                        currentState.setSelected("signup");
                        menuViewModel.setState(currentState);

                        menuController.execute(currentState.getSelected());
                    }
                }
        );

        logIn.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            MenuState currentState = menuViewModel.getState();
                            currentState.setSelected("login");
                            menuViewModel.setState(currentState);

                            menuController.execute(currentState.getSelected());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


//        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MenuState state = (MenuState) evt.getNewValue();
    }
}