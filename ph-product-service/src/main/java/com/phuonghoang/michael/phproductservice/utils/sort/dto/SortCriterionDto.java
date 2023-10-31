package com.phuonghoang.michael.phproductservice.utils.sort.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SortCriterionDto {
    @NotBlank
    private String filterKey;

    @NotBlank
    private String sortOption;
}
