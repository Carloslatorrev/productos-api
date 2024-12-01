package com.example.productosapi.service;

import com.example.productosapi.exception.EstadisticaNotFoundException;
import com.example.productosapi.model.Estadistica;
import com.example.productosapi.repository.EstadisticaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticaServiceTest {

    @InjectMocks
    private EstadisticaService estadisticaService;

    @Mock
    private EstadisticaRepository estadisticaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void actualizarEstadisticas_deberiaIncrementarCantidadSiCategoriaExiste() throws ExecutionException, InterruptedException {
        String categoria = "Electrónica";
        Estadistica estadisticaExistente = new Estadistica();
        estadisticaExistente.setCategoria(categoria);
        estadisticaExistente.setCantidad(5);

        when(estadisticaRepository.findByCategoria(categoria)).thenReturn(Optional.of(estadisticaExistente));

        estadisticaService.actualizarEstadisticas(categoria).get();

        assertEquals(6, estadisticaExistente.getCantidad());
        verify(estadisticaRepository, times(1)).save(estadisticaExistente);
    }

}

