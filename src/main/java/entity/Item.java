package entity;

public class Item {

    private final String name;
    private Tier tier;

    public Item(String name) {
        this.name = name;
        this.tier = Tier.S;
    }

    public String getName() {
        return name;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }
}
