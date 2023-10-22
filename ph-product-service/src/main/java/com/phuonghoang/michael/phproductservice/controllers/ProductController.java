package com.phuonghoang.michael.phproductservice.controllers;

import com.phuonghoang.michael.phproductservice.domain.dto.ProductDto;
import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "products")
@Log4j2
public class ProductController extends BaseCrudController<ProductService, ProductDto, Product, Long> {
    public ProductController(ProductService service, ModelMapper modelMapper) {
        super(service, modelMapper, Product.class, ProductDto.class);
    }
}
