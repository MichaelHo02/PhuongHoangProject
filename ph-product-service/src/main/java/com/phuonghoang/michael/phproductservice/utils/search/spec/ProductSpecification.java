package com.phuonghoang.michael.phproductservice.utils.search.spec;

import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.domain.entity.ProductSearch;
import com.phuonghoang.michael.phproductservice.utils.search.entity.SearchCriterion;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Log4j2
public class ProductSpecification implements Specification<Product> {
    private final SearchCriterion criteria;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        String filterKey = criteria.getFilterKey().toLowerCase();
        String value = criteria.getValue().toString();

        String searchStrContains = String.format("%%%s%%", value.toLowerCase());
        String searchStrBeginsWith = String.format("%s%%", value.toLowerCase());
        String searchStrEndsWith = String.format("%%%s", value.toLowerCase());

        Expression<String> expression = filterKey.equals("categories") ? root.joinSet(filterKey) : root.get(filterKey);

        return switch (Objects.requireNonNull(criteria.getOperation())) {
            case CONTAINS -> builder.like(builder.lower(expression), searchStrContains);
            case DOES_NOT_CONTAIN -> builder.notLike(builder.lower(expression), searchStrContains);
            case EQUAL -> builder.equal(expression, value);
            case NOT_EQUAL -> builder.notEqual(expression, value);
            case BEGINS_WITH -> builder.like(builder.lower(expression), searchStrBeginsWith);
            case DOES_NOT_BEGIN_WITH -> builder.notLike(builder.lower(expression), searchStrBeginsWith);
            case ENDS_WITH -> builder.like(builder.lower(expression), searchStrEndsWith);
            case DOES_NOT_END_WITH -> builder.notLike(builder.lower(expression), searchStrEndsWith);
            case NULL -> builder.isNull(expression);
            case NOT_NULL -> builder.isNotNull(expression);
            case GREATER_THAN -> builder.greaterThan(expression, value);
            case GREATER_THAN_EQUAL -> builder.greaterThanOrEqualTo(expression, value);
            case LESS_THAN -> builder.lessThan(expression, value);
            case LESS_THAN_EQUAL -> builder.lessThanOrEqualTo(expression, value);
        };
    }

    public static class Builder {
        private final ProductSearch productSearch;

        public Builder(ProductSearch productSearch) {
            this.productSearch = productSearch;
        }

        public Specification<Product> build() {
            List<SearchCriterion> searchCriteria = productSearch.getSearchCriteria();
            if (searchCriteria == null || searchCriteria.isEmpty()) {
                return null;
            }

            Specification<Product> result = new ProductSpecification(searchCriteria.remove(0));

            while (!searchCriteria.isEmpty()) {
                SearchCriterion searchCriterion = searchCriteria.remove(0);
                result = switch (productSearch.getDataOption()) {
                    case ALL -> Specification.where(result).and(new ProductSpecification(searchCriterion));
                    case ANY -> Specification.where(result).or(new ProductSpecification(searchCriterion));
                };
            }

            return result;
        }
    }
}
