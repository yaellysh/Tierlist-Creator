package entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TierList {

    public static final int LENGTH = 8;

    private final String name;
    private final Map<String, Item> items;
    private List<User> likes;

    public TierList(String name, List<Item> items) {
        this.name = name;
        this.items = items.stream().collect(Collectors.toMap(Item::getName, s -> s));
    }

    public Item getItem(String name) {
        return this.items.get(name);
    }
  
    public List<Item> getItems() {
        return items.values().stream().toList();
    }

    public Map<String, Tier> getTierList() {
        return this.items.values().stream().collect(Collectors.toMap(Item::getName, Item::getTier));
    }

    public String getName() {
        return this.name;
    }
  
    public List<User> getLikes(){
        return this.likes;
    }

    @Override
    public String toString() {
        return String.join(",", this.items.keySet());
    }
}
