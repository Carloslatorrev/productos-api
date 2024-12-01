package com.example.productosapi.controller;

import com.example.productosapi.model.Producto;
import com.example.productosapi.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    @Test
    void crearProducto_deberiaCrearUnProducto() throws Exception {
        Producto producto = new Producto();
        producto.setNombre("Producto Test");
        producto.setCategoria("Electrónica");

        when(productoService.crearProducto(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Producto Test\",\"categoria\":\"Electrónica\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto Test"))
                .andExpect(jsonPath("$.categoria").value("Electrónica"));
    }

    @Test
    void listarProductos_deberiaListarTodosLosProductos() throws Exception {
        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");
        Producto producto2 = new Producto();
        producto2.setNombre("Producto 2");

        when(productoService.listarProductos()).thenReturn(List.of(producto1, producto2));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Producto 1"))
                .andExpect(jsonPath("$[1].nombre").value("Producto 2"));
    }

    @Test
    void obtenerProductoPorId_deberiaRetornarProductoSiExiste() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");

        when(productoService.obtenerProductoPorId(1L)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto 1"));
    }

    @Test
    void obtenerProductoPorId_deberiaRetornarNotFoundSiNoExiste() throws Exception {
        when(productoService.obtenerProductoPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarProducto_deberiaEliminarElProducto() throws Exception {
        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isNoContent());

        verify(productoService, times(1)).eliminarProducto(1L);
    }
}
