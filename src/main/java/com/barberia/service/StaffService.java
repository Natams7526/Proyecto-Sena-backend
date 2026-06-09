package com.barberia.service;

import com.barberia.dto.StaffDTO;
import com.barberia.dto.StaffResponseDTO;

import java.util.List;

/**
 * Interfaz de servicio para Staff
 * Define operaciones CRUD para barberos/staff
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
public interface StaffService {

    /**
     * Crea un nuevo staff/barbero en la base de datos
     * 
     * @param staffDTO los datos del staff a crear
     * @return StaffResponseDTO con los datos del staff creado
     * @throws IllegalArgumentException si el staff ya existe
     */
    StaffResponseDTO crearStaff(StaffDTO staffDTO);

    /**
     * Actualiza los datos de un staff existente
     * 
     * @param id el ID del staff a actualizar
     * @param staffDTO los datos actualizados
     * @return StaffResponseDTO con los datos del staff actualizado
     * @throws com.barberia.exception.ResourceNotFoundException si el staff no existe
     */
    StaffResponseDTO actualizarStaff(Long id, StaffDTO staffDTO);

    /**
     * Obtiene un staff por su ID
     * 
     * @param id el ID del staff
     * @return StaffResponseDTO con los datos del staff
     * @throws com.barberia.exception.ResourceNotFoundException si el staff no existe
     */
    StaffResponseDTO obtenerStaffPorId(Long id);

    /**
     * Obtiene todos los staff del sistema
     * 
     * @return Lista de StaffResponseDTO
     */
    List<StaffResponseDTO> listarStaff();

    /**
     * Obtiene todos los staff filtrados por estado
     * 
     * @param estado el estado del staff (true = ACTIVO, false = INACTIVO)
     * @return Lista de StaffResponseDTO filtrados por estado
     */
    List<StaffResponseDTO> listarStaffPorEstado(Boolean estado);

    /**
     * Elimina un staff de la base de datos
     * 
     * @param id el ID del staff a eliminar
     * @throws com.barberia.exception.ResourceNotFoundException si el staff no existe
     */
    void eliminarStaff(Long id);
}
