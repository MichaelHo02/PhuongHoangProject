package com.phuonghoang.michael.phproductservice.domain.dto;

public interface BaseDto<T> {
    Boolean isValid(T t);
}
