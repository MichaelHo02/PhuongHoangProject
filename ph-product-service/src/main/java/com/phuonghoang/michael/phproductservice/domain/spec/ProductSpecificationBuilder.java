package com.phuonghoang.michael.phproductservice.domain.spec;

import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.domain.utils.SearchCriteria;
import com.phuonghoang.michael.phproductservice.domain.utils.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationBuilder {
    private final List<SearchCriteria> params;

    public ProductSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public final ProductSpecificationBuilder with(String key, Object value, String operation) {
        params.add(new SearchCriteria(key, value, operation));
        return this;
    }

    public final ProductSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<Product> build() {
        if (params.isEmpty()) {
            return null;
        }

        Specification<Product> result = new ProductSpecification(params.get(0));
        for (int i = 1; i < params.size(); i++) {
            SearchCriteria criteria = params.get(i);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL ?
                    Specification.where(result).and(new ProductSpecification(criteria)) :
                    Specification.where(result).or(new ProductSpecification(criteria));
        }
        return result;
    }
}
