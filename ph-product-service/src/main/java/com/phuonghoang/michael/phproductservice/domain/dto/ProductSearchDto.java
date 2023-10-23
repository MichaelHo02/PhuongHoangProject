package com.phuonghoang.michael.phproductservice.domain.dto;

import com.phuonghoang.michael.phproductservice.domain.utils.SearchCriteria;
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
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;
}
