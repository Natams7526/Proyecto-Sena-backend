package com.barberia.repository;

import com.barberia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Usuario
 * Proporciona operaciones CRUD y consultas personalizadas
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario
     * 
     * @param username el nombre de usuario
     * @return Optional con el usuario si existe
     */
    Optional<Usuario> findByUsername(String username);

    /**
     * Busca un usuario por su email
     * 
     * @param email el email del usuario
     * @return Optional con el usuario si existe
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verifica si existe un usuario con un username específico
     * 
     * @param username el nombre de usuario
     * @return true si existe, false en caso contrario
     */
    boolean existsByUsername(String username);

    /**
     * Verifica si existe un usuario con un email específico
     * 
     * @param email el email del usuario
     * @return true si existe, false en caso contrario
     */
    boolean existsByEmail(String email);
}
