package com.example.productosapi.controller;

import com.example.productosapi.model.Estadistica;
import com.example.productosapi.repository.EstadisticaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstadisticaController {

    private final EstadisticaRepository estadisticaRepository;

    public EstadisticaController(EstadisticaRepository estadisticaRepository) {
        this.estadisticaRepository = estadisticaRepository;
    }

    @GetMapping("/api/estadisticas")
    public List<Estadistica> listarEstadisticas() {
        return estadisticaRepository.findAll();
    }
}
