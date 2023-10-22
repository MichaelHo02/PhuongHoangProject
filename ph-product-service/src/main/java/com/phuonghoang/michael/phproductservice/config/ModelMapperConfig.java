package com.phuonghoang.michael.phproductservice.config;

import com.phuonghoang.michael.phproductservice.domain.dto.ProductDto;
import com.phuonghoang.michael.phproductservice.domain.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
//        modelMapper.addMappings(configMapProductToProductDto());
        return modelMapper;
    }

//    private PropertyMap<Product, ProductDto> configMapProductToProductDto() {
//        return new PropertyMap<>() {
//            @Override
//            protected void configure() {
//                map().setCode(source.getCode());
//                map().setName(source.getName());
//                map().setDescription(source.getDescription());
//                map().setCategories(source.getCategories());
//            }
//        };
//    }
}
