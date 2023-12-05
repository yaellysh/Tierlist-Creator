package view;

import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;

import javax.swing.*;
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

        JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        signUp = new JButton(MenuViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        logIn = new JButton(MenuViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            MenuState currentState = menuViewModel.getState();
                            currentState.setSelected("signup");
                            menuViewModel.setState(currentState);

                            menuController.execute(currentState.getSelected());
                        }
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

        this.add(title);
        this.add(buttons);
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