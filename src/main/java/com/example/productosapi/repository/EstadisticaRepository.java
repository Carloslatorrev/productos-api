package com.example.productosapi.repository;

import com.example.productosapi.model.Estadistica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EstadisticaRepository extends JpaRepository<Estadistica, Long> {
    Optional<Estadistica> findByCategoria(String categoria);
}
