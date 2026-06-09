package com.barberia.service;

import com.barberia.dto.ScheduleDTO;
import com.barberia.dto.ScheduleResponseDTO;

import java.util.List;

/**
 * Interfaz de servicio para Schedule
 * Define operaciones CRUD para horarios
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
public interface ScheduleService {

    /**
     * Crea un nuevo horario para un staff
     * 
     * @param staffId el ID del staff propietario del horario
     * @param scheduleDTO los datos del horario a crear
     * @return ScheduleResponseDTO con los datos del horario creado
     * @throws com.barberia.exception.ResourceNotFoundException si el staff no existe
     */
    ScheduleResponseDTO crearHorario(Long staffId, ScheduleDTO scheduleDTO);

    /**
     * Actualiza los datos de un horario existente
     * 
     * @param id el ID del horario a actualizar
     * @param scheduleDTO los datos actualizados
     * @return ScheduleResponseDTO con los datos del horario actualizado
     * @throws com.barberia.exception.ResourceNotFoundException si el horario no existe
     */
    ScheduleResponseDTO actualizarHorario(Long id, ScheduleDTO scheduleDTO);

    /**
     * Obtiene un horario por su ID
     * 
     * @param id el ID del horario
     * @return ScheduleResponseDTO con los datos del horario
     * @throws com.barberia.exception.ResourceNotFoundException si el horario no existe
     */
    ScheduleResponseDTO obtenerHorarioPorId(Long id);

    /**
     * Obtiene todos los horarios de un staff específico
     * 
     * @param staffId el ID del staff
     * @return Lista de ScheduleResponseDTO
     * @throws com.barberia.exception.ResourceNotFoundException si el staff no existe
     */
    List<ScheduleResponseDTO> listarHorariosPorStaff(Long staffId);

    /**
     * Obtiene todos los horarios para un día específico
     * 
     * @param diaSemana el día de la semana (0-6)
     * @return Lista de ScheduleResponseDTO
     */
    List<ScheduleResponseDTO> listarHorariosPorDia(Integer diaSemana);

    /**
     * Obtiene todos los horarios de un staff para un día específico
     * 
     * @param staffId el ID del staff
     * @param diaSemana el día de la semana (0-6)
     * @return Lista de ScheduleResponseDTO
     * @throws com.barberia.exception.ResourceNotFoundException si el staff no existe
     */
    List<ScheduleResponseDTO> listarHorariosPorStaffYDia(Long staffId, Integer diaSemana);

    /**
     * Elimina un horario de la base de datos
     * 
     * @param id el ID del horario a eliminar
     * @throws com.barberia.exception.ResourceNotFoundException si el horario no existe
     */
    void eliminarHorario(Long id);
}
