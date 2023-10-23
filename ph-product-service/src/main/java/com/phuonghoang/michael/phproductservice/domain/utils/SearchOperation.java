package com.phuonghoang.michael.phproductservice.domain.utils;

public enum SearchOperation {
    CONTAINS, DOES_NOT_CONTAIN, EQUAL, NOT_EQUAL, BEGINS_WITH, DOES_NOT_BEGIN_WITH,
    ENDS_WITH, DOES_NOT_END_WITH, NULL, NOT_NULL, GREATER_THAN, GREATER_THAN_EQUAL,
    LESS_THAN, LESS_THAN_EQUAL, ANY, ALL;

    public static final String[] SIMPLE_OPERATION_SET = {
            "CONTAINS", "DOES_NOT_CONTAIN", "EQUAL", "NOT_EQUAL", "BEGINS_WITH", "DOES_NOT_BEGIN_WITH",
            "ENDS_WITH", "DOES_NOT_END_WITH", "NULL", "NOT_NULL", "GREATER_THAN", "GREATER_THAN_EQUAL",
            "LESS_THAN", "LESS_THAN_EQUAL"
    };

    public static SearchOperation getDataOption(final String dataOption) {
        return switch (dataOption) {
            case "ALL" -> ALL;
            case "ANY" -> ANY;
            default -> null;
        };
    }

    public static SearchOperation getOperation(final String input) {
        return switch (input) {
            case "CONTAINS" -> CONTAINS;
            case "DOES_NOT_CONTAIN" -> DOES_NOT_CONTAIN;
            case "EQUAL" -> EQUAL;
            case "NOT_EQUAL" -> NOT_EQUAL;
            case "BEGINS_WITH" -> BEGINS_WITH;
            case "DOES_NOT_BEGIN_WITH" -> DOES_NOT_BEGIN_WITH;
            case "ENDS_WITH" -> ENDS_WITH;
            case "DOES_NOT_END_WITH" -> DOES_NOT_END_WITH;
            case "NULL" -> NULL;
            case "NOT_NULL" -> NOT_NULL;
            case "GREATER_THAN" -> GREATER_THAN;
            case "GREATER_THAN_EQUAL" -> GREATER_THAN_EQUAL;
            case "LESS_THAN" -> LESS_THAN;
            case "LESS_THAN_EQUAL" -> LESS_THAN_EQUAL;
            default -> null;
        };
    }
}
