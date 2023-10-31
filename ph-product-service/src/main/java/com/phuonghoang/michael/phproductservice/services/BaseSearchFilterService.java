package com.phuonghoang.michael.phproductservice.services;

import org.springframework.data.domain.Page;

public interface BaseSearchFilterService <DOMAIN, SEARCH_DOMAIN> {
    Page<DOMAIN> findBySearchCriteria(Integer pageNum, Integer pageSize, SEARCH_DOMAIN searchDomain);

}
