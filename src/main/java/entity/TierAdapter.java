package entity;

import java.awt.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TierAdapter {

    public static final TierAdapter S = new TierAdapter(Tier.S, Color.RED);
    public static final TierAdapter A = new TierAdapter(Tier.A, Color.ORANGE);
    public static final TierAdapter B = new TierAdapter(Tier.B, Color.YELLOW);
    public static final TierAdapter C = new TierAdapter(Tier.C, Color.GREEN);
    public static final TierAdapter D = new TierAdapter(Tier.D, Color.CYAN);
    public static final TierAdapter[] TIERS = {S, A, B, C, D};

    private final Tier tier;
    private final Color color;

    public TierAdapter(Tier tier, Color color) {
        this.tier = tier;
        this.color = color;
    }

    public static Map<String, Tier> getTiers() {
        return Stream.of(TIERS).collect(Collectors.toMap(TierAdapter::getName, TierAdapter::getTier));
    }

    public String getName() {
        return String.valueOf(tier);
    }

    public Color getColor() {
        return color;
    }

    public Tier getTier() {
        return tier;
    }
}

