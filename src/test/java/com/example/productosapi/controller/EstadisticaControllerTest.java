package com.example.productosapi.controller;

import com.example.productosapi.model.Estadistica;
import com.example.productosapi.repository.EstadisticaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class EstadisticaControllerTest {

    @Mock
    private EstadisticaRepository estadisticaRepository;

    @InjectMocks
    private EstadisticaController estadisticaController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(estadisticaController).build();
    }

    @Test
    void listarEstadisticasTest() throws Exception {
        Estadistica estadistica1 = new Estadistica();
        estadistica1.setId(1L);
        estadistica1.setCategoria("Estadística 1");
        estadistica1.setCantidad(10);

        Estadistica estadistica2 = new Estadistica();
        estadistica2.setId(2L);
        estadistica2.setCategoria("Estadística 2");
        estadistica2.setCantidad(20);

        List<Estadistica> estadisticas = Arrays.asList(estadistica1, estadistica2);
        when(estadisticaRepository.findAll()).thenReturn(estadisticas);

        mockMvc.perform(get("/api/estadisticas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].categoria").value("Estadística 1"))
                .andExpect(jsonPath("$[0].cantidad").value(10))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].categoria").value("Estadística 2"))
                .andExpect(jsonPath("$[1].cantidad").value(20));
    }
}
