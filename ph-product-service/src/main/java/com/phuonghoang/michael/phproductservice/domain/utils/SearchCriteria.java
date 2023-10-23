package com.phuonghoang.michael.phproductservice.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String filterKey;
    private Object value;
    private String operation;
    private String dataOption;

    public SearchCriteria(String filterKey, Object value, String operation) {
        this.filterKey = filterKey;
        this.value = value;
        this.operation = operation;
    }
}
