package com.barberia.service.impl;

import com.barberia.dto.ProductDTO;
import com.barberia.dto.ProductResponseDTO;
import com.barberia.exception.ResourceNotFoundException;
import com.barberia.mapper.ProductMapper;
import com.barberia.model.Product;
import com.barberia.repository.ProductRepository;
import com.barberia.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Product
 * Proporciona la lógica de negocio para operaciones CRUD
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    /**
     * Inyección del repositorio de Product
     */
    private final ProductRepository productRepository;

    /**
     * Inyección del mapper de Product
     */
    private final ProductMapper productMapper;

    /**
     * Crea un nuevo producto en la base de datos
     */
    @Override
    public ProductResponseDTO crearProducto(ProductDTO productDTO) {
        log.info("Creando nuevo producto: {}", productDTO.getNombre());

        // Validar que el producto no exista
        if (productRepository.existsByNombre(productDTO.getNombre())) {
            log.warn("Intento de crear producto con nombre duplicado: {}", productDTO.getNombre());
            throw new IllegalArgumentException("El producto '" + productDTO.getNombre() + "' ya existe");
        }

        // Crear el producto
        Product producto = productMapper.dtoToProduct(productDTO);
        Product productoGuardado = productRepository.save(producto);

        log.info("Producto creado exitosamente con ID: {}", productoGuardado.getId());
        return productMapper.productToResponseDTO(productoGuardado);
    }

    /**
     * Actualiza los datos de un producto existente
     */
    @Override
    public ProductResponseDTO actualizarProducto(Long id, ProductDTO productDTO) {
        log.info("Actualizando producto con ID: {}", id);

        // Buscar el producto
        Product producto = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Producto no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Producto no encontrado con ID: " + id);
                });

        // Validar si el nombre cambió y si el nuevo ya existe
        if (!producto.getNombre().equals(productDTO.getNombre()) &&
                productRepository.existsByNombre(productDTO.getNombre())) {
            log.warn("Intento de actualizar a nombre duplicado: {}", productDTO.getNombre());
            throw new IllegalArgumentException("El producto '" + productDTO.getNombre() + "' ya existe");
        }

        // Actualizar los datos
        producto.setNombre(productDTO.getNombre());
        producto.setEstado(productDTO.getEstado());
        producto.setStockDisponible(productDTO.getStockDisponible());
        producto.setCantidad(productDTO.getCantidad());
        producto.setPrecio(productDTO.getPrecio());

        Product productoActualizado = productRepository.save(producto);

        log.info("Producto actualizado exitosamente con ID: {}", id);
        return productMapper.productToResponseDTO(productoActualizado);
    }

    /**
     * Obtiene un producto por su ID
     */
    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO obtenerProductoPorId(Long id) {
        log.info("Buscando producto con ID: {}", id);

        Product producto = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Producto no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Producto no encontrado con ID: " + id);
                });

        return productMapper.productToResponseDTO(producto);
    }

    /**
     * Obtiene todos los productos del sistema
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> listarProductos() {
        log.info("Obteniendo todos los productos");

        return productRepository.findAll()
                .stream()
                .map(productMapper::productToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los productos filtrados por estado
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> listarProductosPorEstado(Boolean estado) {
        log.info("Obteniendo productos con estado: {}", estado);

        return productRepository.findByEstado(estado)
                .stream()
                .map(productMapper::productToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Elimina un producto de la base de datos
     */
    @Override
    public void eliminarProducto(Long id) {
        log.info("Eliminando producto con ID: {}", id);

        Product producto = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Producto no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Producto no encontrado con ID: " + id);
                });

        productRepository.delete(producto);
        log.info("Producto eliminado exitosamente con ID: {}", id);
    }
}
