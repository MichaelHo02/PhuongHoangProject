package com.phuonghoang.michael.phproductservice.services;

import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.domain.entity.ProductSearch;

public interface ProductService extends BaseCrudService<Product, Long>, BaseSearchFilterService<Product, ProductSearch> {
}
