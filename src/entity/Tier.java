package entity;

import java.awt.*;

public class Tier {

    public static final Tier S = new Tier("S", Color.RED);
    public static final Tier A = new Tier("A", Color.ORANGE);
    public static final Tier B = new Tier("B", Color.YELLOW);
    public static final Tier C = new Tier("C", Color.GREEN);
    public static final Tier D = new Tier("D", Color.BLUE);
    public static final Tier[] TIERS = {S, A, B, C, D};

    private final String name;
    private final Color color;

    public Tier(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}

