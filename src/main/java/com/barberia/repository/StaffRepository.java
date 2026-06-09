package com.barberia.repository;

import com.barberia.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Staff
 * Proporciona operaciones CRUD y consultas personalizadas
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    /**
     * Busca un staff por su nombre
     * 
     * @param nombre el nombre del staff/barbero
     * @return Optional con el staff si existe
     */
    Optional<Staff> findByNombre(String nombre);

    /**
     * Busca todos los staff filtrados por estado
     * 
     * @param estado el estado del staff (true = ACTIVO, false = INACTIVO)
     * @return Lista de staff con el estado especificado
     */
    List<Staff> findByEstado(Boolean estado);

    /**
     * Verifica si existe un staff con un nombre específico
     * 
     * @param nombre el nombre del staff
     * @return true si existe, false en caso contrario
     */
    boolean existsByNombre(String nombre);
}
