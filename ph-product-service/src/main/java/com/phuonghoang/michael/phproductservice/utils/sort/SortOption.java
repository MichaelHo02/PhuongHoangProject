package com.phuonghoang.michael.phproductservice.utils.sort;

import java.util.HashMap;
import java.util.Map;

public enum SortOption {
    ASC("asc"),
    DEC("dec"),
    ;

    private final String value;
    private static final Map<String, SortOption> BY_VALUE = new HashMap<>();

    SortOption(String value) {
        this.value = value;
    }

    static {
        for (SortOption option : values()) {
            BY_VALUE.put(option.value, option);
        }
    }

    public static SortOption toEnum(String value) {
        return BY_VALUE.get(value);
    }
}
