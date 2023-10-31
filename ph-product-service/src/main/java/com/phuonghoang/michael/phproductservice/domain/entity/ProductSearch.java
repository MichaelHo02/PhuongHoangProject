package com.phuonghoang.michael.phproductservice.domain.entity;

import com.phuonghoang.michael.phproductservice.utils.search.SearchDataOption;
import com.phuonghoang.michael.phproductservice.utils.search.entity.SearchCriterion;
import com.phuonghoang.michael.phproductservice.utils.sort.entity.SortCriterion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearch {
    private List<SortCriterion> sortCriteria;
    private List<SearchCriterion> searchCriteria;
    private SearchDataOption dataOption;
}
