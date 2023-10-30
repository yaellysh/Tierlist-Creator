package entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TierList {

    private final List<Item> items;
    private List<User> likes;

    public TierList(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public Map<String, Tier> getTierList() {
        return this.items.stream().collect(Collectors.toMap(Item::getName, Item::getTier));
    }

    public List<User> getLikes(){
        return this.likes;
    }
}
