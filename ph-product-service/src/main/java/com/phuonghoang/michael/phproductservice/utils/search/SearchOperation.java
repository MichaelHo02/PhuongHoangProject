package com.phuonghoang.michael.phproductservice.utils.search;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum SearchOperation {
    CONTAINS("cn"),
    DOES_NOT_CONTAIN("nc"),
    EQUAL("eq"),
    NOT_EQUAL("ne"),
    BEGINS_WITH("bw"),
    DOES_NOT_BEGIN_WITH("bn"),
    ENDS_WITH("ew"),
    DOES_NOT_END_WITH("en"),
    NULL("nu"),
    NOT_NULL("nn"),
    GREATER_THAN("gt"),
    GREATER_THAN_EQUAL("ge"),
    LESS_THAN("lt"),
    LESS_THAN_EQUAL("le");

    private final String value;
    private static final Map<String, SearchOperation> BY_VALUE = new HashMap<>();

    SearchOperation(String value) {
        this.value = value;
    }

    static {
        for (SearchOperation operation: values()) {
            BY_VALUE.put(operation.value, operation);
        }
    }

    public static SearchOperation toEnum(String value) {
        return BY_VALUE.get(value);
    }
}
