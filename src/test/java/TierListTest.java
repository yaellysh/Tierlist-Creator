import data_access.FileUserDataAccessObject;
import entity.Tier;
import entity.TierList;
import org.junit.Test;
import use_case.tierlist.TierListDataAccessInterface;
import view.TierListView;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

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
        System.out.println(updatedTier);
        assert updatedTier.equals(Tier.A);
    }
    // TODO add test case for home button once implemented
    private static Tier getTierList() throws IOException {
        TierListDataAccessInterface object = new FileUserDataAccessObject("src/main/resources/users.json");
        return object.getUser("Yael").getTierList("Test").getItem("Item 1").getTier();
    }
}
