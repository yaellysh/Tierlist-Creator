package use_case.generate;

import entity.TierList;
import entity.User;

public interface GenerateTierListDataAccessInterface {

    void addTierList(User user, TierList tierList);

}
