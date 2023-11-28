package use_case.generate.random_tierlist;

import entity.Item;

import java.util.Map;

public interface RandomTierListDataAccessInterface {

    Map<String, Item> generateTierList(String prompt);

}
