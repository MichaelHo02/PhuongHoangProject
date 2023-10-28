package com.phuonghoang.michael.phproductservice.utils.search.entity;

import com.phuonghoang.michael.phproductservice.utils.search.SearchOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriterion {
    private String filterKey;
    private Object value;
    private SearchOperation operation;
}
