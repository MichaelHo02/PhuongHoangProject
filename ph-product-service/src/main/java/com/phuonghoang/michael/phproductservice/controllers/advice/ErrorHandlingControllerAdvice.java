package com.phuonghoang.michael.phproductservice.controllers.advice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    List<Violation> onConstraintValidationException(ConstraintViolationException e) {
        List<Violation> violations = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            violations.add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return violations;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    List<Violation> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Violation> violations = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            violations.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return violations;
    }

    @Data
    @AllArgsConstructor
    public static class Violation {
        private final String fieldName;
        private final String message;
    }

}
