package com.barberia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para respuesta de Usuario - Datos del usuario sin información sensible
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    /**
     * Identificador del usuario
     */
    private Long id;

    /**
     * Nombre completo del usuario
     */
    private String nombre;

    /**
     * Email del usuario
     */
    private String email;

    /**
     * Nombre de usuario
     */
    private String username;

    /**
     * Rol del usuario
     */
    private String rol;

    /**
     * Estado del usuario
     */
    private Boolean activo;
}
