package view;

import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final MenuViewModel menuViewModel;
    private JTextField usernameInputField = new JTextField(15);
    private JPasswordField passwordInputField = new JPasswordField(15);
    private JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;

    private final JButton signUp;
    private final JButton cancel;

    public SignupView(SignupController controller, SignupViewModel signupViewModel) {

        this.signupController = controller;
        signupViewModel.addPropertyChangeListener(this);
        this.menuViewModel = new MenuViewModel();

        JLabel signUpTitleLabel = new JLabel(SignupViewModel.TITLE_LABEL);
        signUpTitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        signUpTitleLabel.setFont(SignupViewModel.TITLE_FONT);
        this.add(signUpTitleLabel);
        signUpTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        InputPanel usernameInfo = new InputPanel(SignupViewModel.USERNAME_LABEL);
        usernameInputField = usernameInfo.getTextField();

        PasswordInputPanel passwordInfo = new PasswordInputPanel(SignupViewModel.PASSWORD_LABEL);
        passwordInputField = passwordInfo.getTextField();

        PasswordInputPanel repeatPasswordInfo = new PasswordInputPanel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        repeatPasswordInputField = repeatPasswordInfo.getTextField();

        JPanel buttons = new JPanel();
        buttons.setBorder(new EmptyBorder(30, 0, 0,0));

        ButtonPanel signUpButtonPanel = new ButtonPanel(SignupViewModel.SIGNUP_BUTTON_LABEL);
        ButtonPanel cancelButtonPanel = new ButtonPanel(SignupViewModel.CANCEL_BUTTON_LABEL);

        buttons.add(cancelButtonPanel);
        buttons.add(signUpButtonPanel);

        this.signUp = signUpButtonPanel.getButton();
        this.cancel = cancelButtonPanel.getButton();

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(signUp)) {
                        SignupState currentState = signupViewModel.getState();

                        signupController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getRepeatPassword()
                        );
                    }
                }
        );

        cancel.addActionListener(
                e -> {
                    if (e.getSource().equals(cancel)) {
                        signupController.returnToMenu(menuViewModel);
                    }
                }
        );

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState); // Hmm, is this necessary?
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
        this.add(new JSeparator());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}