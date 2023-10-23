package com.phuonghoang.michael.phproductservice.controllers;

import com.phuonghoang.michael.phproductservice.domain.dto.ProductDto;
import com.phuonghoang.michael.phproductservice.domain.dto.ProductSearchDto;
import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.domain.spec.ProductSpecificationBuilder;
import com.phuonghoang.michael.phproductservice.domain.utils.SearchCriteria;
import com.phuonghoang.michael.phproductservice.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "products")
@Log4j2
public class ProductController extends BaseCrudController<ProductService, ProductDto, Product, Long> {
    public ProductController(ProductService service, ModelMapper modelMapper) {
        super(service, modelMapper, Product.class, ProductDto.class);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestBody ProductSearchDto productSearchDto
    ) {
        ProductSpecificationBuilder builder = new ProductSpecificationBuilder();
        List<SearchCriteria> criteriaList = productSearchDto.getSearchCriteriaList();
        if (criteriaList != null) {
            criteriaList.forEach(x -> {
                x.setDataOption(productSearchDto.getDataOption());
                builder.with(x);
            });
        }
        Pageable pageable = PageRequest.of(
                pageNum,
                pageSize,
                Sort.by("code")
                        .ascending()
                        .and(Sort.by("name"))
                        .ascending());
        Page<Product> productPage = service.findBySearchCriteria(builder.build(), pageable);
        return new ResponseEntity<>(productPage.toList(), HttpStatus.OK);
    }
}
