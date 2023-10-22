package com.phuonghoang.michael.phproductservice.controllers;

import com.phuonghoang.michael.phproductservice.domain.dto.ProductDto;
import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "products")
@AllArgsConstructor
@Log4j2
public class ProductController implements BaseCrudController<ProductDto, Long> {
    private ProductService productService;
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ProductDto> create(ProductDto productDto) {
        log.info("Product-Create {}", productDto.getName(), productDto.getDescription());
        Product product = modelMapper.map(productDto, Product.class);
        Product createdProduct = productService.create(product);
        ProductDto createdProductDto = modelMapper.map(createdProduct, ProductDto.class);
        return new ResponseEntity<>(createdProductDto, HttpStatus.CREATED);
//        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductDto> get(Long id) {
        log.info("Product-Get {}", id);
        Product product = productService.get(id);
        log.warn("Product Info {}", product.getCategories());
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> update(Long id, ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productService.update(product, id);
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);
        return new ResponseEntity<>(savedProductDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> delete(Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
