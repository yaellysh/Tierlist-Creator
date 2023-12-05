package use_case.random_tierlist;

import entity.Item;

import java.util.List;

public interface RandomTierListDataAccessInterface {

    List<Item> generateTierList(String prompt);

}
