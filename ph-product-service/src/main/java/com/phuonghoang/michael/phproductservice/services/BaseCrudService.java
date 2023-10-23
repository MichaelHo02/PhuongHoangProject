package com.phuonghoang.michael.phproductservice.services;

import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface BaseCrudService<T, ID> {
    T create(T t);
    T get(ID id);
    T update(T t, ID id);
    Boolean delete(ID id);

    Page<Product> findBySearchCriteria(Specification<Product> specification, Pageable pageable);
}
