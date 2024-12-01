package com.example.productosapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleProductNotFound(ProductNotFoundException ex) {
        return ex.getMessage();
    }

    // Manejador de excepciones para Estadística
    @ExceptionHandler(EstadisticaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleEstadisticaNotFound(EstadisticaNotFoundException ex) {
        return ex.getMessage();
    }
}
