package com.phuonghoang.michael.phproductservice.services;

public interface BaseCrudService<T, ID> {
    T create(T t);
    T get(ID id);
    T update(T t, ID id);
    Boolean delete(ID id);
}
