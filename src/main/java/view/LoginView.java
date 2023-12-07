package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";

    private final MenuViewModel menuViewModel;

    private JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;
    private final LoginController loginController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller, MenuViewModel menuViewModel) {

        this.loginController = controller;
        loginViewModel.addPropertyChangeListener(this);
        this.menuViewModel = menuViewModel;

        JLabel loginTitleLabel = new JLabel(LoginViewModel.TITLE_LABEL);
        loginTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        loginTitleLabel.setFont(LoginViewModel.TITLE_FONT);
        this.add(loginTitleLabel);
        loginTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        InputPanel usernameInfo = new InputPanel(LoginViewModel.USERNAME_LABEL);
        usernameInputField = usernameInfo.getTextField();

        PasswordInputPanel passwordInfo = new PasswordInputPanel(LoginViewModel.PASSWORD_LABEL);
        passwordInputField = passwordInfo.getTextField();

        JPanel buttons = new JPanel();
        buttons.setBorder(new EmptyBorder(30, 0, 0,0));

        ButtonPanel loginButtonPanel = new ButtonPanel(LoginViewModel.LOGIN_BUTTON_LABEL);
        ButtonPanel cancelButtonPanel = new ButtonPanel(LoginViewModel.CANCEL_BUTTON_LABEL);

        buttons.add(cancelButtonPanel);
        buttons.add(loginButtonPanel);

        this.logIn = loginButtonPanel.getButton();
        this.cancel = cancelButtonPanel.getButton();


        logIn.addActionListener(
                evt -> {
                    if (evt.getSource().equals(logIn)) {
                        usernameInputField.setText("");
                        passwordInputField.setText("");

                        LoginState currentState = loginViewModel.getState();

                        loginController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            usernameInputField.setText("");
                            passwordInputField.setText("");
                            LoginState currentState = loginViewModel.getState();

                            loginController.returnToMenu(menuViewModel);
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(loginTitleLabel);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
        this.add(new JSeparator());
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        } else if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
        }
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}