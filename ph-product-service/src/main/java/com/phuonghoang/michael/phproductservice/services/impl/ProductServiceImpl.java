package com.phuonghoang.michael.phproductservice.services.impl;

import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.domain.entity.ProductSearch;
import com.phuonghoang.michael.phproductservice.repositories.ProductRepository;
import com.phuonghoang.michael.phproductservice.services.ProductService;
import com.phuonghoang.michael.phproductservice.utils.search.spec.ProductSpecification;
import com.phuonghoang.michael.phproductservice.utils.sort.PageableBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product update(Product product, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product.setCode(id);
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<Product> findBySearchCriteria(Integer pageNum, Integer pageSize, ProductSearch productSearch) {
        ProductSpecification.Builder productSpecificationBuilder = new ProductSpecification.Builder(productSearch);
        PageableBuilder pageableBuilder = PageableBuilder.builder()
                .withSortCriterion(productSearch.getSortCriteria())
                .withPageNum(pageNum)
                .withPageSize(pageSize)
                .build();
        return productRepository.findAll(productSpecificationBuilder.build(), pageableBuilder.build());
    }
}
