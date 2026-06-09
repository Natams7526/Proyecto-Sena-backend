package com.barberia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO para respuesta de Producto - Datos del producto sin información sensible
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    /**
     * Identificador del producto
     */
    private Long id;

    /**
     * Nombre del producto
     */
    private String nombre;

    /**
     * Estado del producto (true = ACTIVO, false = INACTIVO)
     */
    private Boolean estado;

    /**
     * Stock disponible del producto
     */
    private Integer stockDisponible;

    /**
     * Cantidad de unidades
     */
    private Integer cantidad;

    /**
     * Precio del producto
     */
    private BigDecimal precio;
}
