package com.barberia.service;

import com.barberia.dto.ProductDTO;
import com.barberia.dto.ProductResponseDTO;

import java.util.List;

/**
 * Interfaz de servicio para Product
 * Define operaciones CRUD para productos
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
public interface ProductService {

    /**
     * Crea un nuevo producto en la base de datos
     * 
     * @param productDTO los datos del producto a crear
     * @return ProductResponseDTO con los datos del producto creado
     * @throws IllegalArgumentException si el producto ya existe
     */
    ProductResponseDTO crearProducto(ProductDTO productDTO);

    /**
     * Actualiza los datos de un producto existente
     * 
     * @param id el ID del producto a actualizar
     * @param productDTO los datos actualizados
     * @return ProductResponseDTO con los datos del producto actualizado
     * @throws com.barberia.exception.ResourceNotFoundException si el producto no existe
     */
    ProductResponseDTO actualizarProducto(Long id, ProductDTO productDTO);

    /**
     * Obtiene un producto por su ID
     * 
     * @param id el ID del producto
     * @return ProductResponseDTO con los datos del producto
     * @throws com.barberia.exception.ResourceNotFoundException si el producto no existe
     */
    ProductResponseDTO obtenerProductoPorId(Long id);

    /**
     * Obtiene todos los productos del sistema
     * 
     * @return Lista de ProductResponseDTO
     */
    List<ProductResponseDTO> listarProductos();

    /**
     * Obtiene todos los productos filtrados por estado
     * 
     * @param estado el estado del producto (true = ACTIVO, false = INACTIVO)
     * @return Lista de ProductResponseDTO filtrados por estado
     */
    List<ProductResponseDTO> listarProductosPorEstado(Boolean estado);

    /**
     * Elimina un producto de la base de datos
     * 
     * @param id el ID del producto a eliminar
     * @throws com.barberia.exception.ResourceNotFoundException si el producto no existe
     */
    void eliminarProducto(Long id);
}
