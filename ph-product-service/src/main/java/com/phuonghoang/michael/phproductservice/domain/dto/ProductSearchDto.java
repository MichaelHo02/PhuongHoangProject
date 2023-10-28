package com.phuonghoang.michael.phproductservice.domain.dto;

import com.phuonghoang.michael.phproductservice.utils.search.dto.SearchCriterionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDto {
    private List<SearchCriterionDto> searchCriteria;
    private String dataOption;
}
