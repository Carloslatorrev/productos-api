package com.example.productosapi.service;

import com.example.productosapi.exception.ProductNotFoundException;
import com.example.productosapi.model.Producto;
import com.example.productosapi.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final EstadisticaService estadisticaService;

    public ProductoService(ProductoRepository productoRepository, EstadisticaService estadisticaService) {
        this.productoRepository = productoRepository;
        this.estadisticaService = estadisticaService;
    }

    public Producto crearProducto(Producto producto) {
        Producto nuevoProducto = productoRepository.save(producto);
        estadisticaService.actualizarEstadisticas(nuevoProducto.getCategoria());
        return nuevoProducto;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " does not exist for deletion.");
        }
        productoRepository.deleteById(id);
    }

    // Nuevo método para actualizar un producto
    public Producto actualizarProducto(Long id, Producto producto) {
        // Verificar si el producto existe
        if (!productoRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }

        // Asignar el ID del producto recibido al producto a actualizar
        producto.setId(id);
        Producto productoActualizado = productoRepository.save(producto);

        // Actualizamos las estadísticas después de la actualización (si es necesario)
        estadisticaService.actualizarEstadisticas(productoActualizado.getCategoria());

        return productoActualizado;
    }

    // Nuevo método para buscar productos por nombre
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre); // Asumimos que tienes este método en el repositorio
    }
}
