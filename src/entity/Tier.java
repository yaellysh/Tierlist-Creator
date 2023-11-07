package entity;

import java.util.Arrays;

public enum Tier {
    S, A, B, C;

    public static String[] getTiers(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}

