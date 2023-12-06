import data_access.FileUserDataAccessObject;
import entity.Tier;
import entity.TierList;
import entity.User;
import interface_adapter.menu.MenuViewModel;
import org.junit.Test;
import use_case.custom_tierlist.CustomTierListInputData;
import use_case.custom_tierlist.CustomTierListInteractor;
import use_case.custom_tierlist.CustomTierListOutputBoundary;
import use_case.custom_tierlist.CustomTierListOutputData;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import use_case.tierlist.TierListDataAccessInterface;
import view.TierListView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class TierListTest {

    @Test
    public void testWriteToFile() {
        FileUserDataAccessObject object = new FileUserDataAccessObject("src/test/resources/users.json");
        SignupInteractor interactor = new SignupInteractor(object, new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {}

            @Override
            public void prepareFailView(String error) {}

            @Override
            public void returnToMenu(MenuViewModel menuViewModel) {}
        });
        interactor.execute(new SignupInputData("Yael", "potatoes", "potatoes"));

        CustomTierListInteractor customTierListInteractor = new CustomTierListInteractor(new CustomTierListOutputBoundary() {
            @Override
            public void prepareSuccessView(CustomTierListOutputData data) {

            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void prepareBackView() {

            }
        }, object);
        User user = object.getUser("Yael");
        String[] items = new String[TierList.LENGTH];
        for (int i = 0; i < TierList.LENGTH; i++) {
            items[i] = ("Item " + i);
        }

        customTierListInteractor.execute(new CustomTierListInputData(user, "Test", items));

        // TODO: Change tier
    }

    @Test
    public void testReadFromFile() {
        testWriteToFile();
        FileUserDataAccessObject object = new FileUserDataAccessObject("src/test/resources/users.json");
        assert object
                .getUser("Yael")
                .getTierList("Test")
                .getItem("Item 1")
                .getTier()
                .equals(Tier.S);
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

//    @Test
//    // This is broken and will need to be fixed when we do end-to-end testing
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
