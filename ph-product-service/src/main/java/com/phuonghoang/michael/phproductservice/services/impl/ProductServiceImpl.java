package com.phuonghoang.michael.phproductservice.services.impl;

import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.repositories.ProductRepository;
import com.phuonghoang.michael.phproductservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
    public Page<Product> findBySearchCriteria(Specification<Product> specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable);
    }
}
