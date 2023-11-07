package use_case.tierlist;

import entity.Tier;

public interface TierListDataAccessInterface {

    void saveTier(String user, String tierList, String item, Tier tier);


}
