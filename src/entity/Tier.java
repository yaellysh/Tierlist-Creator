package entity;

import java.util.Arrays;

public enum Tier {
    S, A, B, C;

    public static String[] getTiers() {
        return Arrays.stream(Tier.values()).map(Enum::name).toArray(String[]::new);
    }
}

