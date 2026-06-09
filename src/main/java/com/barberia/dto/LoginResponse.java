package com.barberia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para respuesta de Login - Datos devueltos después de autenticación
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    /**
     * Mensaje de respuesta del login
     */
    private String mensaje;

    /**
     * Indica si el login fue exitoso
     */
    private Boolean exitoso;

    /**
     * Datos del usuario loggeado (sin contraseña)
     */
    private UsuarioResponseDTO usuario;
}
