package com.example.productosapi.exception;

public class EstadisticaNotFoundException extends RuntimeException {

    public EstadisticaNotFoundException(String categoria) {
        super("Estadística no encontrada para la categoría: " + categoria);
    }
}
