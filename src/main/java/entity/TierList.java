package entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TierList {

    public static final int LENGTH = 10;

    private final String name;
    private final Map<String, Item> items;

    public TierList(String name, List<Item> items) {
        this.name = name;
        this.items = items.stream().collect(Collectors.toMap(Item::getName, s -> s));
    }

    public Item getItem(String name) {
        return this.items.get(name);
    }

    public Map<String, Tier> getTierList() {
        return this.items.values().stream().collect(Collectors.toMap(Item::getName, Item::getTier));
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.join(",", this.items.keySet());
    }

}
