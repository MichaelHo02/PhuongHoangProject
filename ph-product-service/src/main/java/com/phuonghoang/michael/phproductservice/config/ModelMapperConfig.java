package com.phuonghoang.michael.phproductservice.config;

import com.phuonghoang.michael.phproductservice.domain.dto.ProductSearchDto;
import com.phuonghoang.michael.phproductservice.domain.entity.ProductSearch;
import com.phuonghoang.michael.phproductservice.utils.search.SearchDataOption;
import com.phuonghoang.michael.phproductservice.utils.search.SearchOperation;
import com.phuonghoang.michael.phproductservice.utils.sort.SortOption;
import com.phuonghoang.michael.phproductservice.utils.search.dto.SearchCriterionDto;
import com.phuonghoang.michael.phproductservice.utils.sort.dto.SortCriterionDto;
import com.phuonghoang.michael.phproductservice.utils.search.entity.SearchCriterion;
import com.phuonghoang.michael.phproductservice.utils.sort.entity.SortCriterion;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
        modelMapper.addMappings(configMapProductSearchDtoToProductSearch());
        modelMapper.addMappings(configMapSearchCriteriaDtoToSearchCriteria());

        return modelMapper;
    }

    private PropertyMap<ProductSearchDto, ProductSearch> configMapProductSearchDtoToProductSearch() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setDataOption(SearchDataOption.toEnum(source.getDataOption()));
                using(new AbstractConverter<List<SearchCriterionDto>, List<SearchCriterion>>() {
                    @Override
                    protected List<SearchCriterion> convert(List<SearchCriterionDto> source) {
                        if (source == null) return null;
                        return source.stream()
                                .map(searchCriterionDto -> SearchCriterion.builder()
                                        .filterKey(searchCriterionDto.getFilterKey())
                                        .value(searchCriterionDto.getValue())
                                        .operation(searchCriterionDto.getOperation() == null ? null :
                                                SearchOperation.toEnum(searchCriterionDto.getOperation()))
                                        .build())
                                .collect(Collectors.toList());
                    }
                }).map(source.getSearchCriteria()).setSearchCriteria(null);

                using(new AbstractConverter<List<SortCriterionDto>, List<SortCriterion>>() {
                    @Override
                    protected List<SortCriterion> convert(List<SortCriterionDto> source) {
                        if (source == null) return null;
                        return source.stream()
                                .map(sortCriterionDto -> SortCriterion.builder()
                                        .filterKey(sortCriterionDto.getFilterKey())
                                        .sortOption(sortCriterionDto.getSortOption() == null ? null :
                                                SortOption.toEnum(sortCriterionDto.getSortOption()))
                                        .build())
                                .collect(Collectors.toList());
                    }
                }).map(source.getSortCriteria()).setSortCriteria(null);
            }
        };
    }

    private PropertyMap<SearchCriterionDto, SearchCriterion> configMapSearchCriteriaDtoToSearchCriteria() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setFilterKey(source.getFilterKey());
                map().setValue(source.getValue());
                map().setOperation(source.getOperation() == null ? null :
                        SearchOperation.toEnum(source.getOperation()));
            }
        };
    }
}
