package com.phuonghoang.michael.phproductservice.utils.search.entity;

import com.phuonghoang.michael.phproductservice.utils.search.SearchOperation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriterion {
    @NotBlank
    private String filterKey;

    @NotNull
    private Object value;

    @NotNull
    private SearchOperation operation;
}
