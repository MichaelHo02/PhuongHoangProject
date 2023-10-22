package com.phuonghoang.michael.phproductservice.repositories;

import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
