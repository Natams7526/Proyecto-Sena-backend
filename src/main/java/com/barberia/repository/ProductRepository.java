package com.barberia.repository;

import com.barberia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Product
 * Proporciona operaciones CRUD y consultas personalizadas
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Busca un producto por su nombre
     * 
     * @param nombre el nombre del producto
     * @return Optional con el producto si existe
     */
    Optional<Product> findByNombre(String nombre);

    /**
     * Busca todos los productos por estado
     * 
     * @param estado el estado del producto (true = ACTIVO, false = INACTIVO)
     * @return Lista de productos con el estado especificado
     */
    List<Product> findByEstado(Boolean estado);

    /**
     * Verifica si existe un producto con un nombre específico
     * 
     * @param nombre el nombre del producto
     * @return true si existe, false en caso contrario
     */
    boolean existsByNombre(String nombre);
}
