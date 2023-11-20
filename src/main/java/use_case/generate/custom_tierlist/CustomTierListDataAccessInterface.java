package use_case.generate.custom_tierlist;

import entity.TierList;
import entity.User;

public interface CustomTierListDataAccessInterface {

    void addTierList(User user, TierList tierList);

}
