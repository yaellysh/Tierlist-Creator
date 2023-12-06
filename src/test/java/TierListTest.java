import data_access.FileUserDataAccessObject;
import def.DefaultCustomTierListOutputBoundary;
import def.DefaultSignupOutputBoundary;
import def.DefaultTierListOutputBoundary;
import entity.Tier;
import entity.TierList;
import entity.User;
import org.junit.Test;
import use_case.custom_tierlist.CustomTierListInputData;
import use_case.custom_tierlist.CustomTierListInteractor;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;
import use_case.tierlist.TierListDataAccessInterface;
import use_case.tierlist.TierListInputData;
import use_case.tierlist.TierListInteractor;
import view.TierListView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class TierListTest {

    public FileUserDataAccessObject instantiate() {
        FileUserDataAccessObject object = new FileUserDataAccessObject("src/test/resources/users.json");
        SignupInteractor signupInteractor = new SignupInteractor(object, new DefaultSignupOutputBoundary());
        signupInteractor.execute(new SignupInputData("Yael", "potatoes", "potatoes"));
        return object;
    }

    @Test
    public void testCustomTierList() {
        FileUserDataAccessObject object = instantiate();
        CustomTierListInteractor customTierListInteractor = new CustomTierListInteractor(new DefaultCustomTierListOutputBoundary(), object);
        User user = object.getUser("Yael");
        String[] items = new String[TierList.LENGTH];
        for (int i = 0; i < TierList.LENGTH; i++) {
            items[i] = ("Item " + i);
        }

        customTierListInteractor.execute(new CustomTierListInputData(user, "Test", items));

        TierListInteractor tierListInteractor = new TierListInteractor(object, new DefaultTierListOutputBoundary());
        tierListInteractor.execute(new TierListInputData("Yael", "Test", "Item 3", Tier.B));
        assert user.getTierList("Test").getItem("Item 3").getTier().equals(Tier.B);
        assert object
                .getUser("Yael")
                .getTierList("Test")
                .getItem("Item 1")
                .getTier()
                .equals(Tier.S);
        object.removeUser("Yael");
    }

    @Test
    public void testRandomTierList() {

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
