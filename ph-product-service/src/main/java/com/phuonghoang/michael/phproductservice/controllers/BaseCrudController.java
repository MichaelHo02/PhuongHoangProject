package com.phuonghoang.michael.phproductservice.controllers;

import com.phuonghoang.michael.phproductservice.services.BaseCrudService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Log4j2
public abstract class BaseCrudController<SERVICE extends BaseCrudService<SRC, ID>, DTO, SRC, ID> {
    protected SERVICE service;
    protected ModelMapper modelMapper;

    final Class<SRC> srcClass;
    final Class<DTO> dtoClass;

    @PostMapping
    ResponseEntity<DTO> create(@RequestBody DTO dto) {
        log.info("[Product] create {}", dto.toString());

        SRC src = modelMapper.map(dto, srcClass);
        SRC createdSrc = service.create(src);
        DTO createdDto = modelMapper.map(createdSrc, dtoClass);

        log.info("[Product] create result {}", createdDto.toString());
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    ResponseEntity<DTO> get(@PathVariable("id") ID id) {
        log.info("[{}] get {}", srcClass.toString(), id);

        SRC src = service.get(id);
        DTO dto = modelMapper.map(src, dtoClass);

        log.info("[{}] get result {}", srcClass.toString(), dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    ResponseEntity<DTO> update(@PathVariable("id") ID id, @RequestBody DTO dto) {
        log.info("[{}}] update {} {}", srcClass.toString(), id, dto.toString());

        SRC src = modelMapper.map(dto, srcClass);
        SRC savedSrc = service.update(src, id);
        DTO savedDto = modelMapper.map(savedSrc, dtoClass);

        log.info("[{}}] update result {}", srcClass.toString(), savedDto.toString());
        return new ResponseEntity<>(savedDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<SRC> delete(@PathVariable("id") ID id) {
        log.info("[{}] delete {}", srcClass.toString(), id);

        Boolean result = service.delete(id);

        log.info("[{}] delete result {}", srcClass.toString(), result);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
