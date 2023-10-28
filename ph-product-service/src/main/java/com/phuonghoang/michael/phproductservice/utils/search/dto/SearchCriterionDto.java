package com.phuonghoang.michael.phproductservice.utils.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriterionDto {
    private String filterKey;
    private Object value;
    private String operation;
}
