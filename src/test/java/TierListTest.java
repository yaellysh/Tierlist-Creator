import data_access.FileUserDataAccessObject;
import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;
import org.junit.Test;
import use_case.tierlist.TierListDataAccessInterface;
import view.TierListView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class TierListTest {

    @Test
    public void testWriteToFile() {
        // TODO: in the future, this will done via SignupFactory and CreationFactory
        FileUserDataAccessObject object = new FileUserDataAccessObject("src/main/resources/users.json");
        User user = new User("Yael");
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Item item = new Item("Item " + i);
            item.setTier(Tier.S);
            items.add(item);
        }
        TierList tierList = new TierList("Test", items);
        user.addTierList(tierList);
//        object.addUser(user);
        object.save();

        // TODO: will assert in the future that the reading works once others are implemented
    }

    @Test
    public void testReadFromFile() {
        testWriteToFile();
        FileUserDataAccessObject object = new FileUserDataAccessObject("src/main/resources/users.json");
        assert object
                .getUser("Yael")
                .getTierList("Test")
                .getItem("Item 1")
                .getTier()
                .equals(Tier.S);
    }

    // TODO add test case for home button once implemented
    private static Tier getTierList() throws IOException {
        TierListDataAccessInterface object = new FileUserDataAccessObject("src/main/resources/users.json");
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
    public void checkDropDown() throws IOException, InterruptedException {
        Main.main(null);
        Tier initialTier = getTierList();
        assert initialTier.equals(Tier.S);
        JComboBox dropDown = getDropDown();
        Thread.sleep(1000);
        dropDown.setSelectedItem("A");
        Thread.sleep(1000);

        Tier updatedTier = getTierList();
        assert updatedTier.equals(Tier.A);
        dropDown.setSelectedItem("S");
        Thread.sleep(1000);
    }
}
