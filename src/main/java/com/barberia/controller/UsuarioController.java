package com.barberia.controller;

import com.barberia.dto.LoginRequest;
import com.barberia.dto.LoginResponse;
import com.barberia.dto.UsuarioDTO;
import com.barberia.dto.UsuarioResponseDTO;
import com.barberia.service.UsuarioService;
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
 * Controlador REST para gestión de Usuarios
 * Proporciona endpoints CRUD y autenticación
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    /**
     * Inyección del servicio de Usuario
     */
    private final UsuarioService usuarioService;

    /**
     * POST /api/v1/usuarios
     * Crea un nuevo usuario
     * 
     * @param usuarioDTO los datos del usuario a crear
     * @return ResponseEntity con el usuario creado y estado 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("POST /api/v1/usuarios - Creando nuevo usuario");

        UsuarioResponseDTO usuarioCreado = usuarioService.crearUsuario(usuarioDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario creado exitosamente");
        respuesta.put("usuario", usuarioCreado);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * GET /api/v1/usuarios
     * Obtiene todos los usuarios
     * 
     * @return ResponseEntity con la lista de usuarios y estado 200 OK
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerTodosLosUsuarios() {
        log.info("GET /api/v1/usuarios - Obteniendo todos los usuarios");

        List<UsuarioResponseDTO> usuarios = usuarioService.obtenerTodosLosUsuarios();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("total", usuarios.size());
        respuesta.put("usuarios", usuarios);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * GET /api/v1/usuarios/{id}
     * Obtiene un usuario por su ID
     * 
     * @param id el ID del usuario
     * @return ResponseEntity con el usuario y estado 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerUsuarioPorId(@PathVariable Long id) {
        log.info("GET /api/v1/usuarios/{} - Obteniendo usuario", id);

        UsuarioResponseDTO usuario = usuarioService.obtenerUsuarioPorId(id);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("usuario", usuario);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * PUT /api/v1/usuarios/{id}
     * Actualiza un usuario existente
     * 
     * @param id el ID del usuario a actualizar
     * @param usuarioDTO los datos actualizados
     * @return ResponseEntity con el usuario actualizado y estado 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("PUT /api/v1/usuarios/{} - Actualizando usuario", id);

        UsuarioResponseDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario actualizado exitosamente");
        respuesta.put("usuario", usuarioActualizado);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * DELETE /api/v1/usuarios/{id}
     * Elimina un usuario
     * 
     * @param id el ID del usuario a eliminar
     * @return ResponseEntity con mensaje de confirmación y estado 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarUsuario(@PathVariable Long id) {
        log.info("DELETE /api/v1/usuarios/{} - Eliminando usuario", id);

        usuarioService.eliminarUsuario(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario eliminado exitosamente");

        return ResponseEntity.ok(respuesta);
    }

    /**
     * POST /api/v1/usuarios/login
     * Autentica un usuario con su username y password
     * 
     * @param loginRequest los datos de autenticación (username y password)
     * @return ResponseEntity con LoginResponse y estado 200 OK
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("POST /api/v1/usuarios/login - Login de usuario: {}", loginRequest.getUsername());

        LoginResponse loginResponse = usuarioService.login(loginRequest);

        return ResponseEntity.ok(loginResponse);
    }
}
