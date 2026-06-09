package com.barberia.service;

import com.barberia.dto.LoginRequest;
import com.barberia.dto.LoginResponse;
import com.barberia.dto.UsuarioDTO;
import com.barberia.dto.UsuarioResponseDTO;

import java.util.List;

/**
 * Interfaz de servicio para Usuario
 * Define operaciones CRUD y autenticación de usuarios
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
public interface UsuarioService {

    /**
     * Crea un nuevo usuario en la base de datos
     * 
     * @param usuarioDTO los datos del usuario a crear
     * @return UsuarioResponseDTO con los datos del usuario creado
     * @throws IllegalArgumentException si el usuario o email ya existe
     */
    UsuarioResponseDTO crearUsuario(UsuarioDTO usuarioDTO);

    /**
     * Actualiza los datos de un usuario existente
     * 
     * @param id el ID del usuario a actualizar
     * @param usuarioDTO los datos actualizados
     * @return UsuarioResponseDTO con los datos del usuario actualizado
     * @throws com.barberia.exception.UsuarioNotFoundException si el usuario no existe
     */
    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    /**
     * Obtiene un usuario por su ID
     * 
     * @param id el ID del usuario
     * @return UsuarioResponseDTO con los datos del usuario
     * @throws com.barberia.exception.UsuarioNotFoundException si el usuario no existe
     */
    UsuarioResponseDTO obtenerUsuarioPorId(Long id);

    /**
     * Obtiene todos los usuarios del sistema
     * 
     * @return Lista de UsuarioResponseDTO
     */
    List<UsuarioResponseDTO> obtenerTodosLosUsuarios();

    /**
     * Elimina un usuario de la base de datos
     * 
     * @param id el ID del usuario a eliminar
     * @throws com.barberia.exception.UsuarioNotFoundException si el usuario no existe
     */
    void eliminarUsuario(Long id);

    /**
     * Autentica un usuario con sus credenciales
     * 
     * @param loginRequest los datos de login (username y password)
     * @return LoginResponse con el resultado de la autenticación
     * @throws com.barberia.exception.CredencialesInvalidasException si las credenciales son inválidas
     */
    LoginResponse login(LoginRequest loginRequest);
}
