package com.barberia.controller;

import com.barberia.dto.ProductDTO;
import com.barberia.dto.ProductResponseDTO;
import com.barberia.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestión de Productos
 * Proporciona endpoints CRUD para operaciones con productos
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    /**
     * Inyección del servicio de Product
     */
    private final ProductService productService;

    /**
     * POST /api/v1/productos
     * Crea un nuevo producto
     * 
     * @param productDTO los datos del producto a crear
     * @return ResponseEntity con el producto creado y estado 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearProducto(@Valid @RequestBody ProductDTO productDTO) {
        log.info("POST /api/v1/productos - Creando nuevo producto");

        ProductResponseDTO productoCreado = productService.crearProducto(productDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto creado exitosamente");
        respuesta.put("producto", productoCreado);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * GET /api/v1/productos
     * Obtiene todos los productos
     * 
     * @return ResponseEntity con lista de productos y estado 200 OK
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> listarProductos() {
        log.info("GET /api/v1/productos - Listando todos los productos");

        List<ProductResponseDTO> productos = productService.listarProductos();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Productos obtenidos exitosamente");
        respuesta.put("total", productos.size());
        respuesta.put("productos", productos);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * GET /api/v1/productos?estado=true
     * Obtiene productos filtrados por estado
     * 
     * @param estado el estado del producto (true = ACTIVO, false = INACTIVO)
     * @return ResponseEntity con lista de productos filtrados y estado 200 OK
     */
    @GetMapping(params = "estado")
    public ResponseEntity<Map<String, Object>> listarProductosPorEstado(
            @RequestParam Boolean estado) {
        log.info("GET /api/v1/productos?estado={} - Listando productos por estado", estado);

        List<ProductResponseDTO> productos = productService.listarProductosPorEstado(estado);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Productos filtrados obtenidos exitosamente");
        respuesta.put("total", productos.size());
        respuesta.put("productos", productos);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * GET /api/v1/productos/{id}
     * Obtiene un producto por su ID
     * 
     * @param id el ID del producto
     * @return ResponseEntity con el producto y estado 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerProductoPorId(@PathVariable Long id) {
        log.info("GET /api/v1/productos/{} - Obteniendo producto por ID", id);

        ProductResponseDTO producto = productService.obtenerProductoPorId(id);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto obtenido exitosamente");
        respuesta.put("producto", producto);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * PUT /api/v1/productos/{id}
     * Actualiza un producto existente
     * 
     * @param id el ID del producto a actualizar
     * @param productDTO los datos actualizados del producto
     * @return ResponseEntity con el producto actualizado y estado 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {
        log.info("PUT /api/v1/productos/{} - Actualizando producto", id);

        ProductResponseDTO productoActualizado = productService.actualizarProducto(id, productDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto actualizado exitosamente");
        respuesta.put("producto", productoActualizado);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * DELETE /api/v1/productos/{id}
     * Elimina un producto
     * 
     * @param id el ID del producto a eliminar
     * @return ResponseEntity sin contenido y estado 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        log.info("DELETE /api/v1/productos/{} - Eliminando producto", id);

        productService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }
}
