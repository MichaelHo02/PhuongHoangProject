package com.phuonghoang.michael.phproductservice.utils.sort.entity;

import com.phuonghoang.michael.phproductservice.utils.sort.SortOption;
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
public class SortCriterion {
    @NotBlank
    private String filterKey;

    @NotNull
    private SortOption sortOption;
}
