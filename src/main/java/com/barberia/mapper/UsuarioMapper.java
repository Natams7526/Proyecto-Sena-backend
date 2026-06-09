package com.barberia.mapper;

import com.barberia.dto.UsuarioDTO;
import com.barberia.dto.UsuarioResponseDTO;
import com.barberia.model.Usuario;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidad Usuario y DTOs
 * Proporciona métodos para mapeo bidireccional
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Component
public class UsuarioMapper {

    /**
     * Convierte una entidad Usuario a UsuarioDTO
     * 
     * @param usuario la entidad Usuario
     * @return UsuarioDTO con los datos del usuario
     */
    public UsuarioDTO usuarioToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .rol(usuario.getRol())
                .activo(usuario.getActivo())
                .build();
    }

    /**
     * Convierte un UsuarioDTO a entidad Usuario
     * 
     * @param usuarioDTO el DTO del usuario
     * @return Usuario con los datos del DTO
     */
    public Usuario dtoToUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nombre(usuarioDTO.getNombre())
                .email(usuarioDTO.getEmail())
                .username(usuarioDTO.getUsername())
                .password(usuarioDTO.getPassword())
                .rol(usuarioDTO.getRol())
                .activo(usuarioDTO.getActivo())
                .build();
    }

    /**
     * Convierte una entidad Usuario a UsuarioResponseDTO (sin password)
     * 
     * @param usuario la entidad Usuario
     * @return UsuarioResponseDTO sin información sensible
     */
    public UsuarioResponseDTO usuarioToResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .rol(usuario.getRol())
                .activo(usuario.getActivo())
                .build();
    }
}
