package com.phuonghoang.michael.phproductservice.utils.search;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum SearchDataOption {
    ALL("all"),
    ANY("any");

    private final String value;
    private static final Map<String, SearchDataOption> BY_VALUE = new HashMap<>();

    SearchDataOption(String value) {
        this.value = value;
    }

    static {
        for (SearchDataOption option : values()) {
            BY_VALUE.put(option.value, option);
        }
    }

    public static SearchDataOption toEnum(String value) {
        return BY_VALUE.get(value);
    }
}
