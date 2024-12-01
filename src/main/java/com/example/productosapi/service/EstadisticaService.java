package com.example.productosapi.service;

import com.example.productosapi.exception.EstadisticaNotFoundException;
import com.example.productosapi.model.Estadistica;
import com.example.productosapi.repository.EstadisticaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class EstadisticaService {

    private final EstadisticaRepository estadisticaRepository;

    public EstadisticaService(EstadisticaRepository estadisticaRepository) {
        this.estadisticaRepository = estadisticaRepository;
    }

    @Async
    public CompletableFuture<Void> actualizarEstadisticas(String categoria) {
        Optional<Estadistica> estadisticaOpt = estadisticaRepository.findByCategoria(categoria);

        if (estadisticaOpt.isPresent()) {
            Estadistica estadistica = estadisticaOpt.get();
            estadistica.setCantidad(estadistica.getCantidad() + 1);
            estadistica.setFechaActualizacion(java.time.LocalDateTime.now());
            estadisticaRepository.save(estadistica);
        } else {
            Estadistica newEstadistica = new Estadistica();
            newEstadistica.setCategoria(categoria);
            newEstadistica.setFechaActualizacion(java.time.LocalDateTime.now());
            newEstadistica.setCantidad(1);
            estadisticaRepository.save(newEstadistica);
        }

        return CompletableFuture.completedFuture(null);
    }
}
