package com.example.productosapi.service;

import com.example.productosapi.model.Producto;
import com.example.productosapi.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private EstadisticaService estadisticaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearProducto_deberiaGuardarProductoYActualizarEstadisticas() {
        Producto producto = new Producto();
        producto.setNombre("Producto Test");
        producto.setCategoria("Electrónica");

        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.crearProducto(producto);

        assertNotNull(resultado);
        assertEquals("Producto Test", resultado.getNombre());
        verify(estadisticaService, times(1)).actualizarEstadisticas("Electrónica");
    }

    @Test
    void obtenerProductoPorId_deberiaRetornarProductoSiExiste() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.obtenerProductoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Producto 1", resultado.get().getNombre());
    }

    @Test
    void listarProductos_deberiaRetornarListaDeProductos() {
        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");
        Producto producto2 = new Producto();
        producto2.setNombre("Producto 2");

        when(productoRepository.findAll()).thenReturn(List.of(producto1, producto2));

        List<Producto> resultado = productoService.listarProductos();

        assertEquals(2, resultado.size());
        assertEquals("Producto 1", resultado.get(0).getNombre());
    }
}
