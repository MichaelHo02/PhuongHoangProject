package com.phuonghoang.michael.phproductservice.controllers;

import com.phuonghoang.michael.phproductservice.domain.dto.ProductDto;
import com.phuonghoang.michael.phproductservice.domain.dto.ProductSearchDto;
import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import com.phuonghoang.michael.phproductservice.domain.entity.ProductSearch;
import com.phuonghoang.michael.phproductservice.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "products")
@Log4j2
@Validated
public class ProductController extends BaseController<ProductService, ProductDto, Product, Long> {
    public ProductController(ProductService service, ModelMapper modelMapper) {
        super(service, modelMapper, Product.class, ProductDto.class);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(
            @RequestParam(name = "pageNum", defaultValue = "0") @Min(0) Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") @Min(1) Integer pageSize,
            @Valid @RequestBody ProductSearchDto productSearchDto
    ) {
        log.info("[{}] search {}", srcClass.getSimpleName(), productSearchDto.toString());
        ProductSearch productSearch = modelMapper.map(productSearchDto, ProductSearch.class);
        Page<Product> productPage = service.findBySearchCriteria(pageNum, pageSize, productSearch);
        List<ProductDto> resultProducts = productPage.stream()
                .map(element -> modelMapper.map(element, ProductDto.class)).toList();
        log.info("[{}] search result {}", dtoClass.getSimpleName(), resultProducts.toString());
        return new ResponseEntity<>(resultProducts, HttpStatus.OK);
    }
}
