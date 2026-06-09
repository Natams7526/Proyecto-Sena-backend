package com.barberia.service.impl;

import com.barberia.dto.LoginRequest;
import com.barberia.dto.LoginResponse;
import com.barberia.dto.UsuarioDTO;
import com.barberia.dto.UsuarioResponseDTO;
import com.barberia.exception.CredencialesInvalidasException;
import com.barberia.exception.UsuarioNotFoundException;
import com.barberia.mapper.UsuarioMapper;
import com.barberia.model.Usuario;
import com.barberia.repository.UsuarioRepository;
import com.barberia.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Usuario
 * Proporciona la lógica de negocio para operaciones CRUD y autenticación
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    /**
     * Inyección del repositorio de Usuario
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * Inyección del mapper de Usuario
     */
    private final UsuarioMapper usuarioMapper;

    /**
     * Crea un nuevo usuario en la base de datos
     */
    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioDTO usuarioDTO) {
        log.info("Creando nuevo usuario con username: {}", usuarioDTO.getUsername());

        // Validar que el usuario no exista
        if (usuarioRepository.existsByUsername(usuarioDTO.getUsername())) {
            log.warn("Intento de crear usuario con username duplicado: {}", usuarioDTO.getUsername());
            throw new IllegalArgumentException("El username '" + usuarioDTO.getUsername() + "' ya está en uso");
        }

        // Validar que el email no exista
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            log.warn("Intento de crear usuario con email duplicado: {}", usuarioDTO.getEmail());
            throw new IllegalArgumentException("El email '" + usuarioDTO.getEmail() + "' ya está registrado");
        }

        // Crear el usuario
        Usuario usuario = usuarioMapper.dtoToUsuario(usuarioDTO);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        log.info("Usuario creado exitosamente con ID: {}", usuarioGuardado.getId());
        return usuarioMapper.usuarioToResponseDTO(usuarioGuardado);
    }

    /**
     * Actualiza los datos de un usuario existente
     */
    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        log.info("Actualizando usuario con ID: {}", id);

        // Buscar el usuario
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado con ID: {}", id);
                    return new UsuarioNotFoundException("Usuario no encontrado con ID: " + id);
                });

        // Validar si el username cambió y si el nuevo ya existe
        if (!usuario.getUsername().equals(usuarioDTO.getUsername()) &&
                usuarioRepository.existsByUsername(usuarioDTO.getUsername())) {
            log.warn("Intento de actualizar a username duplicado: {}", usuarioDTO.getUsername());
            throw new IllegalArgumentException("El username '" + usuarioDTO.getUsername() + "' ya está en uso");
        }

        // Validar si el email cambió y si el nuevo ya existe
        if (!usuario.getEmail().equals(usuarioDTO.getEmail()) &&
                usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            log.warn("Intento de actualizar a email duplicado: {}", usuarioDTO.getEmail());
            throw new IllegalArgumentException("El email '" + usuarioDTO.getEmail() + "' ya está registrado");
        }

        // Actualizar los datos
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRol(usuarioDTO.getRol());
        usuario.setActivo(usuarioDTO.getActivo());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        log.info("Usuario actualizado exitosamente con ID: {}", id);
        return usuarioMapper.usuarioToResponseDTO(usuarioActualizado);
    }

    /**
     * Obtiene un usuario por su ID
     */
    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {
        log.info("Buscando usuario con ID: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado con ID: {}", id);
                    return new UsuarioNotFoundException("Usuario no encontrado con ID: " + id);
                });

        return usuarioMapper.usuarioToResponseDTO(usuario);
    }

    /**
     * Obtiene todos los usuarios del sistema
     */
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> obtenerTodosLosUsuarios() {
        log.info("Obteniendo todos los usuarios");

        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::usuarioToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Elimina un usuario de la base de datos
     */
    @Override
    public void eliminarUsuario(Long id) {
        log.info("Eliminando usuario con ID: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado con ID: {}", id);
                    return new UsuarioNotFoundException("Usuario no encontrado con ID: " + id);
                });

        usuarioRepository.deleteById(id);

        log.info("Usuario eliminado exitosamente con ID: {}", id);
    }

    /**
     * Autentica un usuario con sus credenciales
     */
    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        log.info("Intento de login para usuario: {}", loginRequest != null ? loginRequest.getUsername() : "<nulo>");

        if (loginRequest == null || !StringUtils.hasText(loginRequest.getUsername()) || !StringUtils.hasText(loginRequest.getPassword())) {
            log.warn("Solicitud de login inválida o incompleta");
            throw new CredencialesInvalidasException("Usuario o contraseña incorrecta");
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(loginRequest.getUsername());

        if (usuarioOptional.isEmpty()) {
            log.warn("Usuario no encontrado en login: {}", loginRequest.getUsername());
            throw new CredencialesInvalidasException("Usuario o contraseña incorrecta");
        }

        Usuario usuario = usuarioOptional.get();
        log.info("Usuario encontrado en login: {}", usuario.getUsername());

        if (!Objects.equals(usuario.getPassword(), loginRequest.getPassword())) {
            log.warn("Credenciales inválidas para usuario: {}", loginRequest.getUsername());
            throw new CredencialesInvalidasException("Usuario o contraseña incorrecta");
        }

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            log.warn("Intento de login con usuario inactivo: {}", loginRequest.getUsername());
            throw new CredencialesInvalidasException("Usuario inactivo");
        }

        log.info("Login exitoso para usuario: {}", loginRequest.getUsername());
        log.info("Antes del mapper");
        UsuarioResponseDTO usuarioResponseDTO = null;
        try {
            usuarioResponseDTO = usuarioMapper.usuarioToResponseDTO(usuario);
            log.info("Después del mapper");
        } catch (Exception ex) {
            log.error("Error al mapear Usuario a UsuarioResponseDTO", ex);
        }

        return LoginResponse.builder()
                .mensaje("Usuario loggeado correctamente")
                .exitoso(true)
                .usuario(usuarioResponseDTO)
                .build();
    }
}
