package com.barberia.mapper;

import com.barberia.dto.ProductDTO;
import com.barberia.dto.ProductResponseDTO;
import com.barberia.model.Product;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidad Product y DTOs
 * Proporciona métodos para mapeo bidireccional
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Component
public class ProductMapper {

    /**
     * Convierte una entidad Product a ProductDTO
     * 
     * @param product la entidad Product
     * @return ProductDTO con los datos del producto
     */
    public ProductDTO productToDTO(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDTO.builder()
                .id(product.getId())
                .nombre(product.getNombre())
                .estado(product.getEstado())
                .stockDisponible(product.getStockDisponible())
                .cantidad(product.getCantidad())
                .precio(product.getPrecio())
                .build();
    }

    /**
     * Convierte un ProductDTO a entidad Product
     * 
     * @param productDTO el DTO del producto
     * @return Product con los datos del DTO
     */
    public Product dtoToProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return Product.builder()
                .id(productDTO.getId())
                .nombre(productDTO.getNombre())
                .estado(productDTO.getEstado())
                .stockDisponible(productDTO.getStockDisponible())
                .cantidad(productDTO.getCantidad())
                .precio(productDTO.getPrecio())
                .build();
    }

    /**
     * Convierte una entidad Product a ProductResponseDTO
     * 
     * @param product la entidad Product
     * @return ProductResponseDTO con los datos del producto
     */
    public ProductResponseDTO productToResponseDTO(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponseDTO.builder()
                .id(product.getId())
                .nombre(product.getNombre())
                .estado(product.getEstado())
                .stockDisponible(product.getStockDisponible())
                .cantidad(product.getCantidad())
                .precio(product.getPrecio())
                .build();
    }

    /**
     * Convierte un ProductDTO a ProductResponseDTO
     * 
     * @param productDTO el DTO del producto
     * @return ProductResponseDTO con los datos del DTO
     */
    public ProductResponseDTO dtoToResponseDTO(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return ProductResponseDTO.builder()
                .id(productDTO.getId())
                .nombre(productDTO.getNombre())
                .estado(productDTO.getEstado())
                .stockDisponible(productDTO.getStockDisponible())
                .cantidad(productDTO.getCantidad())
                .precio(productDTO.getPrecio())
                .build();
    }
}
