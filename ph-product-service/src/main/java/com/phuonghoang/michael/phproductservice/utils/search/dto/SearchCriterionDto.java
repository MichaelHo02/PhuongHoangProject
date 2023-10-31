package com.phuonghoang.michael.phproductservice.utils.search.dto;

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
public class SearchCriterionDto {
    @NotBlank
    private String filterKey;

    @NotNull
    private Object value;

    @NotBlank
    private String operation;
}
