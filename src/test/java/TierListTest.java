import data_access.FileUserDataAccessObject;
import entity.Item;
import entity.Tier;
import entity.TierList;
import entity.User;
import org.junit.Test;

import java.util.ArrayList;

public class TierListTest {

    @Test
    public void testWriteToFile() {
        // TODO: in the future, this will done via SignupFactory and CreationFactory
        FileUserDataAccessObject object = new FileUserDataAccessObject("src/main/resources/users.json");
        User user = new User("Yael");
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Item item = new Item("Item " + i);
            items.add(item);
        }
        TierList tierList = new TierList("Test", items);
        user.addTierList(tierList);
        object.addUser(user);
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

}
