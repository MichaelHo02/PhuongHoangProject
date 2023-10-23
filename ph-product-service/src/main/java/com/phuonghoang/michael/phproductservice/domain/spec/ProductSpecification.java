package com.phuonghoang.michael.phproductservice.domain.spec;

import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.domain.utils.SearchCriteria;
import com.phuonghoang.michael.phproductservice.domain.utils.SearchOperation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashSet;
import java.util.Objects;

@AllArgsConstructor
@Log4j2
public class ProductSpecification implements Specification<Product> {
    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        String filterKey = criteria.getFilterKey().toLowerCase();
        String strToSearch = criteria.getValue().toString().toLowerCase();
        String searchStrContains = String.format("%%%s%%", strToSearch);
        String searchStrBeginsWith = String.format("%s%%", strToSearch);
        String searchStrEndsWith = String.format("%%%s", strToSearch);
        log.error(strToSearch);
        return switch (Objects.requireNonNull(SearchOperation.getOperation(criteria.getOperation()))) {
            case CONTAINS -> filterKey.equals("categories") ?
                    builder.like(builder.lower(root.joinSet(filterKey)), searchStrContains) :
                    builder.like(builder.lower(root.get(filterKey)), searchStrContains);
            case DOES_NOT_CONTAIN -> filterKey.equals("categories") ?
                    builder.notLike(builder.lower(root.joinSet(filterKey)), searchStrContains) :
                    builder.notLike(builder.lower(root.get(filterKey)), searchStrContains);
            case EQUAL -> filterKey.equals("categories") ?
                    builder.equal(root.joinSet(filterKey), criteria.getValue()) :
                    builder.equal(root.get(filterKey), criteria.getValue());
            case NOT_EQUAL -> filterKey.equals("categories") ?
                    builder.notEqual(root.joinSet(filterKey), criteria.getValue()) :
                    builder.notEqual(root.get(filterKey), criteria.getValue());
            case BEGINS_WITH -> filterKey.equals("categories") ?
                    builder.like(builder.lower(root.joinSet(filterKey)), searchStrBeginsWith) :
                    builder.like(builder.lower(root.get(filterKey)), searchStrBeginsWith);
            case DOES_NOT_BEGIN_WITH -> filterKey.equals("categories") ?
                    builder.notLike(builder.lower(root.joinSet(filterKey)), searchStrBeginsWith) :
                    builder.notLike(builder.lower(root.get(filterKey)), searchStrBeginsWith);
            case ENDS_WITH -> filterKey.equals("categories") ?
                    builder.like(builder.lower(root.joinSet(filterKey)), searchStrEndsWith) :
                    builder.like(builder.lower(root.get(filterKey)), searchStrEndsWith);
            case DOES_NOT_END_WITH -> filterKey.equals("categories") ?
                    builder.notLike(builder.lower(root.joinSet(filterKey)), searchStrEndsWith) :
                    builder.notLike(builder.lower(root.get(filterKey)), searchStrEndsWith);
            case NULL -> builder.isNull(root.get(filterKey));
            case NOT_NULL -> builder.isNotNull(root.get(criteria.getFilterKey()));
            case GREATER_THAN -> builder.greaterThan(root.get(criteria.getFilterKey()), strToSearch);
            case GREATER_THAN_EQUAL -> builder.greaterThanOrEqualTo(root.get(criteria.getFilterKey()), strToSearch);
            case LESS_THAN -> builder.lessThan(root.get(criteria.getFilterKey()), strToSearch);
            case LESS_THAN_EQUAL -> builder.lessThanOrEqualTo(root.get(criteria.getFilterKey()), strToSearch);
            default -> null;
        };
    }
}
