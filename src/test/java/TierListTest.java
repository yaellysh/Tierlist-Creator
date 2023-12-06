import factory.SignupFactory;
import org.junit.Test;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class TierListTest {

    public JComboBox getDropDown() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);

        TierListView sv = (TierListView) jp2.getComponent(0);

        return (JComboBox) sv
                .getComponent(3)
                .getComponentAt(0, 0)
                .getComponentAt(73, 0)
                .getComponentAt(89, 5);
    }

    public MenuView getMenuView() throws InterruptedException {
        Main.main(null);

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel viewPanel = (JPanel) jp.getComponent(0);

        MenuView menuView = null;

        for (Component view : viewPanel.getComponents()) {
            if (view instanceof MenuView) {
                menuView = (MenuView) view;
            }
        }
        return menuView;
    }

    public Component getCurrentView() throws InterruptedException {
        Component currentView = null;

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel viewPanel = (JPanel) jp.getComponent(0);

        Component[] cards = viewPanel.getComponents();
        for (Component card : cards) {
            if (card.isVisible()) {
                currentView = card;
            }
        }
        return currentView;
    }

    @Test
    public void checkMenuSignUpButton() throws InterruptedException {
        Main.main(null);

        MenuView menuView = getMenuView();
        assert menuView != null;
        JPanel signUpWrapperPanel = (JPanel) menuView.getComponent(3);
        JPanel signUpPanel = (JPanel) signUpWrapperPanel.getComponent(0);
        JButton signUpButton = (JButton) signUpPanel.getComponent(0);

        signUpButton.doClick();
        Thread.sleep(100);
        Component currentView = getCurrentView();
        assert currentView instanceof SignupView;
    }

    public SignupView getSignupView() throws InterruptedException {
        Main.main(null);

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel viewPanel = (JPanel) jp.getComponent(0);

        SignupView signupView = null;

        for (Component view : viewPanel.getComponents()) {
            if (view instanceof SignupView) {
                signupView = (SignupView) view;
            }
        }
        return signupView;
    }

    @Test
    public void checkMenuLoginButton() throws InterruptedException {
        Main.main(null);

        MenuView menuView = getMenuView();
        assert menuView != null;

        JPanel loginWrapperPanel = (JPanel) menuView.getComponent(7);
        JPanel loginPanel = (JPanel) loginWrapperPanel.getComponent(0);
        JButton loginButton = (JButton) loginPanel.getComponent(0);

        loginButton.doClick();

        Component currentView = getCurrentView();

        assert currentView instanceof LoginView;
    }

    @Test
    public void checkSignUp() throws InterruptedException {
        SignupView signupView = getSignupView();

        InputPanel usernameInputPanel = (InputPanel) signupView.getComponent(1);
        JTextField usernameInput = usernameInputPanel.getTextField();

        PasswordInputPanel passwordInputPanel = (PasswordInputPanel) signupView.getComponent(2);
        JTextField passwordInput = passwordInputPanel.getTextField();

        PasswordInputPanel repeatPasswordInputPanel = (PasswordInputPanel) signupView.getComponent(3);
        JTextField repeatPasswordInput = repeatPasswordInputPanel.getTextField();

        usernameInput.setText("Test");
        passwordInput.setText("Test");
        repeatPasswordInput.setText("Test");

        JPanel buttonPanelWrapper = (JPanel) signupView.getComponent(4);
        ButtonPanel buttonPanel = (ButtonPanel) buttonPanelWrapper.getComponent(1);
        JButton signUpButton = buttonPanel.getButton();

        signUpButton.doClick();

    }

    @Test
    public void checkViewExistingTierlists() {

    }

//    @Test
//    public void checkDropDown() throws IOException, InterruptedException {
//        Main.main(null);
//        Tier initialTier = getTierList();
//        assert initialTier.equals(Tier.S);
//        JComboBox dropDown = getDropDown();
//        Thread.sleep(100);
//        dropDown.setSelectedItem("A");
//        Thread.sleep(100);
//
//        Tier updatedTier = getTierList();
//        assert updatedTier.equals(Tier.A);
//        dropDown.setSelectedItem("S");
//        Thread.sleep(100);
//    }
}
