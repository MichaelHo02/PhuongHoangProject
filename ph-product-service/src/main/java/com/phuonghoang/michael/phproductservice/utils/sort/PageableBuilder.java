package com.phuonghoang.michael.phproductservice.utils.sort;

import com.phuonghoang.michael.phproductservice.domain.dto.ProductDto;
import com.phuonghoang.michael.phproductservice.utils.sort.entity.SortCriterion;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Builder(setterPrefix = "with")
public class PageableBuilder {
    private final List<SortCriterion> sortCriterion;
    private Integer pageNum = 0;
    private Integer pageSize = 10;

    public Pageable build() {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        if (sortCriterion == null || sortCriterion.isEmpty()) {
            pageRequest.withSort(Sort.Direction.ASC, ProductDto.Type.CODE.getValue());
            return pageRequest;
        }

        for (SortCriterion criterion : sortCriterion) {
            Sort.Direction direction = switch (criterion.getSortOption()) {
                case ASC -> Sort.Direction.ASC;
                case DEC -> Sort.Direction.DESC;
            };
            pageRequest = pageRequest.withSort(direction, criterion.getFilterKey());
        }
        return pageRequest;
    }
}
