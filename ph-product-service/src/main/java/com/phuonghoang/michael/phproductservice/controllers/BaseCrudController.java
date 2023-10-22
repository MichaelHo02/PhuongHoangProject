package com.phuonghoang.michael.phproductservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface BaseCrudController<T, ID> {
    @PostMapping
    ResponseEntity<T> create(@RequestBody T t);

    @GetMapping("{id}")
    ResponseEntity<T> get(@PathVariable("id") ID id);

    @PutMapping("{id}")
    ResponseEntity<T> update(@PathVariable("id") ID id, @RequestBody T t);

    @DeleteMapping("{id}")
    ResponseEntity<T> delete(@PathVariable("id") ID id);
}
