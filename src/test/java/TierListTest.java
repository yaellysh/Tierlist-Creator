import data_access.ChatGPTDataAccessObject;
import data_access.FileUserDataAccessObject;
import def.DefaultCustomTierListOutputBoundary;
import def.DefaultRandomTierListOutputBoundary;
import def.DefaultSignupOutputBoundary;
import def.DefaultTierListOutputBoundary;
import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;
import org.junit.Test;
import use_case.custom_tierlist.CustomTierListInputData;
import use_case.custom_tierlist.CustomTierListInteractor;
import use_case.random_tierlist.RandomTierListInputData;
import use_case.random_tierlist.RandomTierListInteractor;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.tierlist.TierListInputData;
import use_case.tierlist.TierListInteractor;
import view.LoginView;
import view.MenuView;
import view.SignupView;
import view.TierListView;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class TierListTest {

    public static FileUserDataAccessObject instantiate() {
        FileUserDataAccessObject object = new FileUserDataAccessObject("src/test/resources/users.json");
        SignupInteractor signupInteractor = new SignupInteractor(object, new DefaultSignupOutputBoundary());
        signupInteractor.execute(new SignupInputData("Yael", "potatoes", "potatoes"));
        return object;
    }

//    @Test
//    public void testCustomTierList() {
//        FileUserDataAccessObject object = instantiate();
//        CustomTierListInteractor customTierListInteractor = new CustomTierListInteractor(new DefaultCustomTierListOutputBoundary(), object);
//        User user = object.getUser("Yael");
//        String[] items = new String[TierList.LENGTH];
//        for (int i = 0; i < TierList.LENGTH; i++) {
//            items[i] = ("Item " + i);
//        }
//        TierList tierList = new TierList("Test", items);
//        user.addTierList(tierList);
//        object.addUser(user);
//        object.save();
//
//        customTierListInteractor.execute(new CustomTierListInputData(user, "Test", items));
//
//        TierListInteractor tierListInteractor = new TierListInteractor(object, new DefaultTierListOutputBoundary());
//        tierListInteractor.execute(new TierListInputData("Yael", "Test", "Item 3", Tier.B));
//        assert user.getTierList("Test").getItem("Item 3").getTier().equals(Tier.B);
//        assert object
//                .getUser("Yael")
//                .getTierList("Test")
//                .getItem("Item 1")
//                .getTier()
//                .equals(Tier.S);
//        object.removeUser("Yael");
//    }

    @Test
    public void testRandomTierList() {
        FileUserDataAccessObject object = instantiate();
        ChatGPTDataAccessObject gptObject = new ChatGPTDataAccessObject();
        RandomTierListInteractor randomTierListInteractor = new RandomTierListInteractor(gptObject, object, new DefaultRandomTierListOutputBoundary());
        randomTierListInteractor.execute(new RandomTierListInputData("Conan Gray songs", object.getUser("Yael")));
        TierList tierList = object.getUser("Yael").getTierList("Conan Gray songs");
        assert tierList != null;
        assert tierList.getItems().size() == TierList.LENGTH;
        assert tierList.getItems().stream().map(Item::getName).anyMatch(s -> s.equals("Heather"));
        tierList.getItems().stream().map(Item::getTier).forEach(s -> {assert s == Tier.S;});
        object.removeUser("Yael");
    }

    // TODO add test case for home button once implemented
    private static Tier getTierList() throws IOException {
        TierListDataAccessInterface object = new FileUserDataAccessObject("src/test/resources/users.json");
        return object.getUser("Yael").getTierList("Test").getItem("Item 1").getTier();
    }

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

    @Test
    public void checkMenuSignUpButton() throws IOException, InterruptedException{
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

        MenuView menuView = (MenuView) viewPanel.getComponent(2);
        JPanel signUpWrapperPanel = (JPanel) menuView.getComponent(3);
        JPanel signUpPanel = (JPanel) signUpWrapperPanel.getComponent(0);
        JButton signUpButton = (JButton) signUpPanel.getComponent(0);

        signUpButton.doClick();

        Component currentView = null;
        Component[] cards = viewPanel.getComponents();
        for (Component card: cards) {
            if (card.isVisible()) {
                currentView = card;
            }
        }
        assert currentView instanceof SignupView;
    }

    @Test
    public void checkMenuLoginButton() throws IOException, InterruptedException{
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

        MenuView menuView = (MenuView) viewPanel.getComponent(2);
        JPanel loginWrapperPanel = (JPanel) menuView.getComponent(7);
        JPanel loginPanel = (JPanel) loginWrapperPanel.getComponent(0);
        JButton loginButton = (JButton) loginPanel.getComponent(0);

        System.out.println(loginButton);

        loginButton.doClick();

        Component currentView = null;
        Component[] cards = viewPanel.getComponents();
        for (Component card: cards) {
            if (card.isVisible()) {
                currentView = card;
            }
        }
        assert currentView instanceof LoginView;
    }
//    @Test
    // This is broken and will need to be fixed when we do end-to-end testing
//    public void checkDropDown() throws IOException, InterruptedException {
//
//
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
